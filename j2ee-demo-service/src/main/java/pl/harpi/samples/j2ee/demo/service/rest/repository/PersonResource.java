package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTOBuilder;
import pl.harpi.samples.j2ee.demo.api.model.PersonLocal;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/v1/repository/persons")
public class PersonResource {
    @Inject
    private PersonLocal personService;

    @GET
    @Path("/{personId}")
    public Response getPerson(@PathParam("personId") String personId, @Context HttpServletRequest request) {
        try {
            PersonDTO person = personService.getPersonById(Long.valueOf(personId));
            return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponse(person, request)).build();
        } catch (ApplicationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response getPersons(@Context HttpServletRequest request) {
        List<PersonDTO> persons = personService.getAllPersons();

        return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponseList(persons, request)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonRequest personRequest, @Context HttpServletRequest request) {
        PersonDTO person = new PersonDTOBuilder()
                .withFirstName(personRequest.getFirstName())
                .withLastName(personRequest.getLastName())
                .build();

        return Response.status(Response.Status.CREATED).entity(RestResponseFactory.createPersonResponse(person, request)).build();
    }
}
