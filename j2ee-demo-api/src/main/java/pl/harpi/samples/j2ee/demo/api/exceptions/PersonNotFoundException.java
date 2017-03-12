package pl.harpi.samples.j2ee.demo.api.exceptions;

public class PersonNotFoundException extends ApplicationException {
    public PersonNotFoundException(Long id) {
        super("Person with id = " + id + " not exists.");
    }
}
