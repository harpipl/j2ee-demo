package pl.harpi.samples.j2ee.demo.person.rest;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.common.rest.BaseResource;
import pl.harpi.samples.j2ee.demo.common.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.person.api.*;
import pl.harpi.samples.j2ee.demo.person.domain.PersonBean;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/repository/persons")
public class PersonResourceImpl extends BaseResource implements PersonResource {
    @Inject
    private PersonBean personService;

    @Override
    public Response getPerson(String personId) {
        try {
            PersonDTO person = personService.getPersonById(Long.valueOf(personId));
            return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponse(person, this.getHttpRequest())).build();
        } catch (ApplicationException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Override
    public Response getPersons(Integer paramStart, Integer paramSize, String sort, OrderType order) {
        int start = (paramStart == null) ? ResourceConstants.PAGING_DEFAULT_START : paramStart;
        int size = (paramSize == null) ? ResourceConstants.PAGING_DEFAULT_SIZE : paramSize;

        QueryProperty sortBy = (sort == null) ? PersonQueryProperty.SORT_BY_DEFAULT : PersonQueryProperty.findByName(sort);

        DataResult dataResult;
        try {
            dataResult = personService.getPersons(new PersonSearchVO(), start, size, sortBy, (order == null) ? PersonQueryProperty.ORDER_BY_DEFAULT : order);
        } catch (ApplicationException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.status(Response.Status.OK).entity(RestResponseFactory.createPersonResponseList(dataResult, this.getHttpRequest())).build();
    }

    @Override
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