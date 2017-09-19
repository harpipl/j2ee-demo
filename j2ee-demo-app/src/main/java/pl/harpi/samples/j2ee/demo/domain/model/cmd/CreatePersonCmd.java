package pl.harpi.samples.j2ee.demo.domain.model.cmd;

import pl.harpi.samples.j2ee.demo.api.base.service.Command;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.domain.model.base.MessagesValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.domain.model.service.PersonBeanContext;
import pl.harpi.samples.j2ee.demo.model.base.ValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

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
