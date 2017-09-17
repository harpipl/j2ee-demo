package pl.harpi.samples.j2ee.demo.maven.plugin;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.parser.ParseException;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public abstract class AbstractDemoMojo extends AbstractMavenReport {
    protected static final String JAVA_FILES = "**\\/*.java";

    private Map<String, ClassLoader> projectClassLoaders = new HashMap<>();

    @Parameter(property = "encoding", defaultValue = "${project.build.sourceEncoding}")
    protected String encoding;

    @Parameter(property = "includes", defaultValue = JAVA_FILES)
    protected String includes;

    @Parameter(property = "excludes")
    protected String excludes;

    public MavenProject getProject() {
        return this.project;
    }

    @Override
    public void execute() throws MojoExecutionException {
        super.execute();
    }

    private void readJavaClasses(MavenProject localProject, List<JavaClassInfo> javaClassInfos) throws IOException, MojoExecutionException {
        JavaClass[] classes = getQdoxClasses(localProject);
        if (classes != null) {
            for (JavaClass jc : classes) {
                javaClassInfos.add(new JavaClassInfo(localProject, jc));
            }
        }

        if (localProject.getCollectedProjects() != null) {
            for (MavenProject prj : localProject.getCollectedProjects()) {
                readJavaClasses(prj, javaClassInfos);
            }
        }
    }

    protected List<JavaClassInfo> readJavaClasses() throws IOException, MojoExecutionException {
        List<JavaClassInfo> javaClassInfos = new ArrayList<>();

        readJavaClasses(project, javaClassInfos);

        javaClassInfos.sort(new Comparator<JavaClassInfo>() {
            @Override
            public int compare(JavaClassInfo o1, JavaClassInfo o2) {
                return o1.getJavaClass().getName().compareTo(o2.getJavaClass().getName());
            }
        });

        return javaClassInfos;
    }

    private List<String> getProjectSourceRoots(MavenProject p) {
        return (p.getCompileSourceRoots() == null
                ? Collections.<String>emptyList()
                : new LinkedList<String>(p.getCompileSourceRoots()));
    }

    private List<String> getCompileClasspathElements(MavenProject p) throws DependencyResolutionRequiredException {
        return (p.getCompileClasspathElements() == null
                ? Collections.<String>emptyList()
                : new LinkedList<String>(p.getCompileClasspathElements()));
    }

    private void loadProjectClassLoader(MavenProject localProject) throws MojoExecutionException {
        ClassLoader projectClassLoader = projectClassLoaders.get(localProject.getName());

        if (projectClassLoader == null) {
            List<String> classPath;
            try {
                classPath = getCompileClasspathElements(localProject);
            } catch (DependencyResolutionRequiredException e) {
                throw new MojoExecutionException("DependencyResolutionRequiredException: " + e.getMessage(), e);
            }

            List<URL> urls = new ArrayList<URL>(classPath.size());
            for (String filename : classPath) {
                try {
                    urls.add(new File(filename).toURL());
                } catch (MalformedURLException e) {
                    throw new MojoExecutionException("MalformedURLException: " + e.getMessage(), e);
                }
            }

            projectClassLoaders.put(localProject.getName(), new URLClassLoader(urls.toArray(new URL[urls.size()]), null));
        }
    }

    private JavaClass[] getQdoxClasses(MavenProject localProject)
            throws IOException, MojoExecutionException {
        if ("pom".equalsIgnoreCase(localProject.getPackaging())) {
            getLog().warn("This project has 'pom' packaging, no Java sources is available.");
            return null;
        }

        List<File> javaFiles = new LinkedList<>();
        for (String sourceRoot : getProjectSourceRoots(localProject)) {
            File f = new File(sourceRoot);
            if (f.isDirectory()) {
                javaFiles.addAll(FileUtils.getFiles(f, DemoMojo.JAVA_FILES, excludes, true));
            } else {
                if (getLog().isWarnEnabled()) {
                    getLog().warn(f + " doesn't exist. Ignored it.");
                }
            }
        }

        JavaDocBuilder builder = new JavaDocBuilder();
        loadProjectClassLoader(localProject);
        builder.getClassLibrary().addClassLoader(projectClassLoaders.get(localProject.getName()));
        builder.setEncoding(encoding);
        for (File f : javaFiles) {
            if (!f.getAbsolutePath().toLowerCase(Locale.ENGLISH).endsWith(".java") && getLog().isWarnEnabled()) {
                getLog().warn("'" + f + "' is not a Java file. Ignored it.");
                continue;
            }

            try {
                builder.addSource(f);
            } catch (ParseException e) {
                if (getLog().isWarnEnabled()) {
                    getLog().warn("QDOX ParseException: " + e.getMessage() + ". Can't fix it.");
                }
            }
        }

        return builder.getClasses();
    }

    @Override
    public String getDescription(Locale locale) {
        return getBundle(locale).getString("report.demo.description");
    }

    @Override
    protected void executeReport(Locale locale) throws MavenReportException {
        new DemoReportGenerator().doGenerateReport(getBundle(locale), this, getSink());
    }


    @Override
    public String getName(Locale locale) {
        return getBundle(locale).getString("report.demo.name");
    }

    private ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle("demo-report", locale, getClass().getClassLoader());
    }
}
