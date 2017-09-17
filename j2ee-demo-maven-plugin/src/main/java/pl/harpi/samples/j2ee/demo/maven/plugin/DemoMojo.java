package pl.harpi.samples.j2ee.demo.maven.plugin;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class DemoMojo extends AbstractDemoMojo {
    @Parameter(defaultValue = "demo-report", property = "outputName", required = true)
    private String outputName;

    @Override
    public String getOutputName() {
        return outputName;
    }
}
