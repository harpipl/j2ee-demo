package pl.harpi.samples.j2ee.demo.service.rest.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.harpi.samples.j2ee.base.service.rest.BaseResource;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class PersonResourceIT {
    private static Log logger = LogFactory.getLog(PersonResourceIT.class);

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        WebArchive archive = getBaseArchive();
        archive.addPackage(PersonResource.class.getPackage());
        archive.as(ZipExporter.class).exportTo(new File("d:/test.zip"), true);
        return archive;

    }

    public static WebArchive getBaseArchive() {
        PomEquippedResolveStage pom = Maven.configureResolver().workOffline().loadPomFromFile("pom.xml");

        return ShrinkWrap
                .create(WebArchive.class)
                .addAsLibraries(pom.resolve("pl.harpi.samples:j2ee-demo-api:jar:?").withoutTransitivity().asFile())
                .addAsLibraries(pom.resolve("pl.harpi.samples:j2ee-demo-model:ejb:?").withoutTransitivity().asFile())
                .addAsLibraries(pom.resolve("pl.harpi.samples:j2ee-demo-domain:ejb:?").withTransitivity().asFile())
                .addAsLibraries(pom.resolve("org.assertj:assertj-core").withoutTransitivity().asFile())
                .addPackage(BaseResource.class.getPackage())
                .addPackage(RestResponseFactory.class.getPackage())
                .addPackage(PersonResource.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("web.xml");
    }

    @Test
    public void getPersons_withoutParamters_shouldReturnWholeData(@ArquillianResteasyResource final WebTarget webTarget) {
        // given

        // when
        final Response response = webTarget.path("/v1/repository/persons")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();

        String result = (response.hasEntity()) ? response.readEntity(String.class) : null;

        // then
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
        assertThat(result).isNotNull();

        response.close();
    }
}
