package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.api.base.types.HttpStatus;
import pl.harpi.samples.j2ee.demo.domain.dao.PersonDAO;
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
    private PersonDAO personDAO;

    @GET
    @Path("/{personId}")
    public Response getPerson(@PathParam("personId") String personId, @Context HttpServletRequest request) {
        IPerson person = personDAO.find(Long.valueOf(personId));
        return Response.status(HttpStatus.OK).entity(RestResponseFactory.createPersonResponse(person, request)).build();

    }

    @GET
    public Response getPersons(@Context HttpServletRequest request) {
        List<IPerson> persons = personDAO.findAll();

        return Response.status(HttpStatus.OK).entity(RestResponseFactory.createPersonResponseList(persons, request)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(PersonRequest personRequest, @Context HttpServletRequest request) {
        IPerson person = personDAO.newPerson();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());

        person = personDAO.saveOrUpdate(person);

        return Response.status(HttpStatus.CREATED).entity(RestResponseFactory.createPersonResponse(person, request)).build();
    }

    @GET
    @Path("/{personId}/addresses")
    public Response getPersonAddresses(@PathParam("personId") String personId, @Context HttpServletRequest request) {
        IPerson person = personDAO.findAndFetchAddresses(Long.valueOf(personId));

        if (person == null) {
            return Response.status(HttpStatus.NOT_FOUND).entity("Could not find a person with id '" + personId + "'.").build();
        }

        return Response.status(HttpStatus.OK).entity(RestResponseFactory.createPersonAddressResponseList(person.getId(), person.getAddresses(), request)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{personId}/addresses")
    public Response createPersonAddress(@PathParam("personId") String personId, AddressRequest addressRequest, @Context HttpServletRequest request) {
        IPerson person = personDAO.find(Long.valueOf(personId));

        if (person == null) {
            return Response.status(HttpStatus.NOT_FOUND).entity("Could not find a person with id '" + personId + "'.").build();
        }

        IAddress address = personDAO.newAddress();
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setType(addressRequest.getType());
        address.setPostalCode(addressRequest.getPostalCode());

        address = personDAO.saveOrUpdateAddress(person.getId(), address);

        return Response.status(HttpStatus.CREATED).entity(RestResponseFactory.createPersonAddressResponse(Long.valueOf(personId), address, request)).build();
    }

    @GET
    @Path("/{personId}/addresses/{addressId}")
    public Response getPersonAddress(@PathParam("personId") String personId, @PathParam("addressId") String addressId, @Context HttpServletRequest request) {
        IPerson person = personDAO.findAndFetchAddresses(Long.valueOf(personId));

        if (person == null) {
            return Response.status(HttpStatus.NOT_FOUND).entity("Could not find a person with id '" + personId + "'.").build();
        }

        IAddress address = null;
        for (IAddress a : person.getAddresses()) {
            if (addressId.equals(String.valueOf(a.getId()))) {
                address = a;
                break;
            }
        }

        return (address == null) ? Response.status(HttpStatus.NOT_FOUND).build() :
                Response.status(HttpStatus.OK).entity(RestResponseFactory.createPersonAddressResponse(person.getId(), address, request)).build();
    }
}
