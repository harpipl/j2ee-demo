/*
 * Copyright 2006-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.harpi.samples.j2ee.demo.person;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import pl.harpi.samples.j2ee.demo.person.domain.PersonBean;
import pl.harpi.samples.j2ee.demo.person.domain.cmd.GetPersonByIdCmd;
import pl.harpi.samples.j2ee.demo.person.rest.PersonResourceImpl;

import java.io.File;

/**
 * @author Christoph Deppisch
 * @since 2.6
 */
public class Deployments {

    private static final String CXF_VERSION = "3.1.14";
    private static final String CXF_GROUP_ID = "org.apache.cxf";

    /**
     * Default employee registry application with REST Http resource.
     *
     * @return
     */
    public static WebArchive employeeWebRegistry() {
        return employeeRegistry()
                .addPackage(PersonBean.class.getPackage())
                .addPackage(GetPersonByIdCmd.class.getPackage())
                .addClasses(PersonResourceImpl.class)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
                .addAsWebInfResource("web.xml", "web.xml");
    }

    private static WebArchive employeeRegistry() {
        File[] file = Maven.resolver().loadPomFromFile("../pom.xml")
                .importCompileAndRuntimeDependencies()
                .resolve("pl.harpi.samples:j2ee-demo-common-api",
                        "pl.harpi.samples:j2ee-demo-common-app", "pl.harpi.samples:j2ee-demo-person-api")
                .withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(file)
                .addAsLibraries(Maven.configureResolver()
                        .workOffline(true)
                        .resolve(CXF_GROUP_ID + ":cxf-rt-frontend-jaxws:" + CXF_VERSION,
                                CXF_GROUP_ID + ":cxf-rt-transports-http:" + CXF_VERSION)
                        .withTransitivity()
                        .asFile());
    }
}
