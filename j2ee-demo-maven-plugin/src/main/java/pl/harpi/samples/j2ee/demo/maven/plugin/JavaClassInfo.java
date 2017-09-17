package pl.harpi.samples.j2ee.demo.maven.plugin;

import com.thoughtworks.qdox.model.JavaClass;
import org.apache.maven.project.MavenProject;

public class JavaClassInfo {
    MavenProject project;
    JavaClass javaClass;

    public JavaClassInfo(MavenProject project, JavaClass javaClass) {
        this.project = project;
        this.javaClass = javaClass;
    }

    public MavenProject getProject() {
        return project;
    }

    public JavaClass getJavaClass() {
        return javaClass;
    }
}
