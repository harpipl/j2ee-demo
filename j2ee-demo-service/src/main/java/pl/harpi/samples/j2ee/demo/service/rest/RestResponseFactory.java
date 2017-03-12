package pl.harpi.samples.j2ee.demo.service.rest;

import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonCollectionResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RestResponseFactory {
    public static PersonResponse createPersonResponse(PersonDTO person, HttpServletRequest request) {
        return new PersonResponse(person, RestUrlBuilder.buildUrl(request, RestUrls.URL_PERSON, person.getId()));
    }

    public static PersonCollectionResponse createPersonResponseList(List<PersonDTO> persons, HttpServletRequest request) {
        List<PersonResponse> responseList = new ArrayList<>();
        for (PersonDTO person : persons) {
            responseList.add(RestResponseFactory.createPersonResponse(person, request));
        }

        return new PersonCollectionResponse(responseList);
    }
}
