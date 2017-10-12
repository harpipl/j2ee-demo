package pl.harpi.samples.j2ee.demo.person.domain.cmd;

import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.common.domain.Command;
import pl.harpi.samples.j2ee.demo.common.domain.MessagesValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.common.domain.ValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.domain.Person;
import pl.harpi.samples.j2ee.demo.person.domain.PersonBeanContext;

public class CreatePersonCmd implements Command<PersonDTO, PersonBeanContext> {
    private PersonDTO personDTO;

    public CreatePersonCmd(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    @Override
    public PersonDTO execute(PersonBeanContext commandContext) throws ApplicationException {
        ValidationNotificationHandler handler = new MessagesValidationNotificationHandler();
        Person person = new Person(personDTO);

        person.validate(handler);

        if (handler.hasErrors()) {
            throw new ApplicationException(handler.getMessages());
        } else {
            commandContext.getRepository().save(person);
            return person.createDTO();
        }
    }
}
