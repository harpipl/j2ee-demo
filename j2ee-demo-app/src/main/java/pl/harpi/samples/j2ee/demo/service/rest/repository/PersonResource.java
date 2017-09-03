package pl.harpi.samples.j2ee.demo.service.rest.repository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pl.harpi.samples.j2ee.base.service.rest.BaseResource;
import pl.harpi.samples.j2ee.base.service.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.*;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/repository/persons")
@Api(value = "PersonResource")
public class PersonResource extends BaseResource {
    @Inject
    private PersonLocal personService;

    @GET
    @Path("/{personId}")
    @ApiOperation(value = "Finds person by ID",
            notes = "Retreves person identified by ID",
            response = PersonResponse.class)
    public Response getPerson(
            @ApiParam(value = "Person identifier", required = true) @PathParam("personId") String personId
    ) {
        try {
            PersonDTO person = personService.getPersonById(Long.valueOf(personId));
            return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponse(person, this.getHttpRequest())).build();
        } catch (ApplicationException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @ApiOperation(value = "Finds persons",
            notes = "Retreves persons",
            response = PersonCollectionResponse.class,
            responseContainer = "List"
    )
    public Response getPersons(
            @ApiParam(value = "Start", required = true) @QueryParam("start") Integer paramStart,
            @ApiParam(value = "Size", required = true) @QueryParam("size") Integer paramSize,
            @ApiParam(value = "Sort", required = true) @QueryParam("sort") String sort,
            @ApiParam(value = "Order", required = true) @QueryParam("order") OrderType order
    ) {
        int start = (paramStart == null) ? ResourceConstants.PAGING_DEFAULT_START : paramStart;
        int size = (paramSize == null) ? ResourceConstants.PAGING_DEFAULT_SIZE : paramSize;

        QueryProperty sortBy = (sort == null) ? PersonQueryProperty.SORT_BY_DEFAULT : PersonQueryProperty.findByName(sort);

        DataResult dataResult = personService.getPersons(new PersonSearchVO(), start, size, sortBy, (order == null) ? PersonQueryProperty.ORDER_BY_DEFAULT : order);

        return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponseList(dataResult, this.getHttpRequest())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates person",
            notes = "Creates person from given input"
    )
    public Response createPerson(
            @ApiParam(value = "Person", required = true) PersonRequest personRequest
    ) {
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