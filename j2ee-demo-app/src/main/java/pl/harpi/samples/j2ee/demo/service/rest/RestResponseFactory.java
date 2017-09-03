package pl.harpi.samples.j2ee.demo.service.rest;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonCollectionResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonResponse;

import javax.servlet.http.HttpServletRequest;

public class RestResponseFactory {
    private RestResponseFactory() {
    }

    public static PersonResponse createPersonResponse(PersonDTO person, HttpServletRequest request) {
        return new PersonResponse(person, RestUrlBuilder.buildUrl(request, RestUrls.URL_PERSON, person.getId()));
    }

    public static PersonCollectionResponse createPersonResponseList(DataResult dataResult, HttpServletRequest request) {
        return new PersonCollectionResponse(dataResult, request);
    }
}