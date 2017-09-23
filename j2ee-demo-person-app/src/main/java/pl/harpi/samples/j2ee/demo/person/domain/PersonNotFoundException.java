package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;

public class PersonNotFoundException extends ApplicationException {
    public PersonNotFoundException(Long id) {
        super("Person with id = " + id + " not exists.");
    }
}
