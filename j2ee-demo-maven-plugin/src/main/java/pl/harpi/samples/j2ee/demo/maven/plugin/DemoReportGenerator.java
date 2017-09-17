package pl.harpi.samples.j2ee.demo.maven.plugin;

import com.thoughtworks.qdox.model.Annotation;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.reporting.MavenReportException;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class DemoReportGenerator {
    public void doGenerateReport(ResourceBundle bundle, AbstractDemoMojo mojo, Sink sink) throws MavenReportException {
        // Sink head
        sink.head();

        sink.title();
        sink.text("Title");
        sink.title_();

        sink.author();
        sink.text("Author");
        sink.author_();

        sink.date();
        sink.text("Date");
        sink.date_();

        sink.head_();
        // Sink head

        sink.body();

        if (mojo.getProject().getParent() == null) {
            List<JavaClassInfo> javaClassInfos;
            try {
                javaClassInfos = mojo.readJavaClasses();
            } catch (MojoExecutionException e) {
                throw new MavenReportException("MojoExecutionException: " + e.getMessage(), e);
            } catch (IOException e) {
                throw new MavenReportException("IOException: " + e.getMessage(), e);
            }

            sink.table();

            sink.tableRow();

            sink.tableHeaderCell();
            sink.text("Module");
            sink.tableHeaderCell_();

            sink.tableHeaderCell();
            sink.text("Project");
            sink.tableHeaderCell_();

            sink.tableHeaderCell();
            sink.text("Class");
            sink.tableHeaderCell_();

            sink.tableHeaderCell();
            sink.text("Annotations");
            sink.tableHeaderCell_();

            sink.tableRow_();

            for (JavaClassInfo javaClassInfo : javaClassInfos) {
                sink.tableRow();

                StringBuilder annotations = new StringBuilder();

                for (Annotation a : javaClassInfo.getJavaClass().getAnnotations()) {
                    annotations.append(", ").append(a.getType().getFullyQualifiedName());
                }

                sink.tableCell();
                sink.text(javaClassInfo.getProject().getName());
                sink.tableCell_();

                sink.tableCell();
                sink.text(javaClassInfo.getJavaClass().getPackageName());
                sink.tableCell_();

                sink.tableCell();
                sink.text(javaClassInfo.getJavaClass().getName());
                sink.tableCell_();

                sink.tableCell();
                sink.text(annotations.toString());
                sink.tableCell_();

                sink.tableRow_();
            }

            sink.table_();
        }

        sink.body_();

        sink.flush();

        sink.close();
    }
}
