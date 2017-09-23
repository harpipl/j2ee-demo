package pl.harpi.samples.j2ee.demo.person.rest;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;

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