package pl.harpi.samples.j2ee.demo.person;

import com.consol.citrus.Citrus;
import com.consol.citrus.annotations.CitrusFramework;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.runner.TestRunner;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(Arquillian.class)
@RunAsClient
public class PersonResourceTest {
    @CitrusFramework
    private Citrus citrusFramework;

    @ArquillianResource
    private URL baseUri;

    private String serviceUri;

    @Before
    public void setUp() throws MalformedURLException {
        serviceUri = new URL(baseUri, "rest/v1/repository/persons").toExternalForm();
    }

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive webArchive = Deployments.employeeWebRegistry();

//        webArchive.as(ZipExporter.class).exportTo(new File("d:/test.war"), true);

        return webArchive;
    }

    @Test
    @InSequence(1)
    @CitrusTest
    public void testGetPersons(@CitrusResource TestRunner citrus) {
        citrus.http(httpActionBuilder-> httpActionBuilder
                .client(serviceUri)
                .send()
                .get()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        citrus.http(httpActionBuilder-> httpActionBuilder
                .client(serviceUri)
                .receive()
                .response(HttpStatus.OK));
    }
}
