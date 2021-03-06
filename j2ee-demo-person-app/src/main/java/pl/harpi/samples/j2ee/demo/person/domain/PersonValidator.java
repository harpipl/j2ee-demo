package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.domain.ValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.common.domain.Validator;

public class PersonValidator extends Validator {
    private Person person;

    public PersonValidator(Person person, ValidationNotificationHandler handler) {
        super(handler);
        setPerson(person);
    }

    @Override
    public void validate() {
        if (getPerson().getFirstName() == null) {
            getNotificationHandler().handleError("First name is null");
        }

        if (getPerson().getLastName() == null) {
            getNotificationHandler().handleError("Last name is null");
        }
    }

    protected Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        this.person = person;
    }
}
