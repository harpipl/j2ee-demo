package pl.harpi.samples.j2ee.demo.person.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface PersonResource {
    @GET
    @Path("/{personId}")
    @ApiOperation(value = "Finds person by ID",
            notes = "Retreves person identified by ID",
            response = PersonResponse.class)
    Response getPerson(@ApiParam(value = "Person identifier", required = true) @PathParam("personId") String personId);

    @GET
    @ApiOperation(value = "Finds persons",
            notes = "Retreves persons",
            response = PersonCollectionResponse.class,
            responseContainer = "List"
    )
    Response getPersons(
            @ApiParam(value = "Start", required = true) @QueryParam("start") Integer paramStart,
            @ApiParam(value = "Size", required = true) @QueryParam("size") Integer paramSize,
            @ApiParam(value = "Sort", required = true) @QueryParam("sort") String sort,
            @ApiParam(value = "Order", required = true) @QueryParam("order") OrderType order
    );

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates person",
            notes = "Creates person from given input"
    )
    Response createPerson(
            @ApiParam(value = "Person", required = true) PersonRequest personRequest
    );
}
