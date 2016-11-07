package pl.harpi.samples.j2ee.demo.service.rest;

import pl.harpi.samples.j2ee.demo.domain.model.Person;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonCollectionResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RestResponseFactory {
    public static PersonResponse createPersonResponse(Person person, HttpServletRequest request) {
        return new PersonResponse(person, RestUrlBuilder.buildUrl(request, RestUrls.URL_PERSON, person.getId()));
    }

    public static PersonCollectionResponse createPersonResponseList(List<Person> persons, HttpServletRequest request) {
        List<PersonResponse> responseList = new ArrayList<>();
        for (Person person : persons) {
            responseList.add(RestResponseFactory.createPersonResponse(person, request));
        }

        return new PersonCollectionResponse(responseList);
    }
}
