package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.base.service.rest.BaseResource;
import pl.harpi.samples.j2ee.base.service.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTOBuilder;
import pl.harpi.samples.j2ee.demo.api.model.PersonLocal;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/repository/persons")
public class PersonResource extends BaseResource {
    @Inject
    private PersonLocal personService;

    @GET
    @Path("/{personId}")
    public Response getPerson(@PathParam("personId") String personId) {
        try {
            PersonDTO person = personService.getPersonById(Long.valueOf(personId));
            return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponse(person, this.getHttpRequest())).build();
        } catch (ApplicationException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    public Response getPersons(@QueryParam("start") Integer paramStart, @QueryParam("size") Integer paramSize) {
        int start = (paramStart == null) ? ResourceConstants.PAGING_DEFAULT_START : paramStart;
        int size = (paramSize == null) ? ResourceConstants.PAGING_DEFAULT_SIZE : paramSize;

        List<PersonDTO> persons = personService.getPersons(new PersonSearchVO(), start, size);

        return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponseList(persons, this.getHttpRequest())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonRequest personRequest) {
        PersonDTO person = new PersonDTOBuilder()
                .withFirstName(personRequest.getFirstName())
                .withLastName(personRequest.getLastName())
                .build();

        try {
            person = personService.savePerson(person);
            return Response.status(Response.Status.CREATED).entity(RestResponseFactory.createPersonResponse(person, this.getHttpRequest())).build();
        } catch (EJBException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (ApplicationException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getObject()).build();
        }

    }
}
