package pl.harpi.samples.j2ee.demo.maven.plugin;

import org.apache.maven.doxia.module.xhtml.XhtmlSink;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.MavenReportException;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ResourceBundle;

import static java.util.Locale.ENGLISH;

public class DemoReportGeneratorTest {
    @Test
    public void test() throws MavenReportException {
        // given
        StringWriter writer = new StringWriter();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("demo-report", ENGLISH);
        DemoReportGenerator generator = new DemoReportGenerator();

        MavenProject mavenProject = new MavenProject();
        mavenProject.setPackaging("pom");

        AbstractDemoMojo mojo = new AbstractDemoMojo() {
            // initialization...
            {
                this.project = mavenProject;
            }

            @Override
            public MavenProject getProject() {
                return mavenProject;
            }

            @Override
            public String getOutputName() {
                return "demo-report";
            }
        };

        // when
        generator.doGenerateReport(resourceBundle, mojo, new XhtmlSink(writer) {
        });
        String xml = writer.toString();

        // then
        System.out.println(xml);
    }
}
