package pl.harpi.samples.j2ee.demo.service.rest;

import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.service.rest.repository.AddressCollectionResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.AddressResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonCollectionResponse;
import pl.harpi.samples.j2ee.demo.service.rest.repository.PersonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RestResponseFactory {
    public static PersonResponse createPersonResponse(IPerson person, HttpServletRequest request) {
        return new PersonResponse(person, RestUrlBuilder.buildUrl(request, RestUrls.URL_PERSON, person.getId()));
    }

    public static AddressResponse createPersonAddressResponse(Long personId, IAddress address, HttpServletRequest request) {
        return new AddressResponse(address, RestUrlBuilder.buildUrl(request, RestUrls.URL_PERSON_ADDRESS, personId, address.getId()));
    }

    public static PersonCollectionResponse createPersonResponseList(List<IPerson> persons, HttpServletRequest request) {
        List<PersonResponse> responseList = new ArrayList<>();
        for (IPerson person : persons) {
            responseList.add(RestResponseFactory.createPersonResponse(person, request));
        }

        return new PersonCollectionResponse(responseList);
    }

    public static AddressCollectionResponse createPersonAddressResponseList(Long personId, List<IAddress> addresses, HttpServletRequest request) {
        List<AddressResponse> responseList = new ArrayList<>();
        for (IAddress address : addresses) {
            responseList.add(RestResponseFactory.createPersonAddressResponse(personId, address, request));
        }

        return new AddressCollectionResponse(responseList);
    }
}
