package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.domain.dao.PersonDAO;
import pl.harpi.samples.j2ee.demo.domain.model.Person;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/repository/persons")
public class PersonResource {
    @Inject
    PersonDAO personDAO;

    @GET
    @Path("/{personId}")
    public Response getPerson(@PathParam("personId") String personId, @Context HttpServletRequest request) {
        Person person = personDAO.find(Long.valueOf(personId));
        return Response.ok(RestResponseFactory.createPersonResponse(person, request)).build();

    }

    @GET
    public Response getPersons(@Context HttpServletRequest request) {
        List<Person> persons = personDAO.findAll();
        return Response.ok(RestResponseFactory.createPersonResponseList(persons, request)).build();
    }
}
