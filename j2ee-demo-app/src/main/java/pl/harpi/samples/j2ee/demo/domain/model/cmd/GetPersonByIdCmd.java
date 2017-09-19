package pl.harpi.samples.j2ee.demo.domain.model.cmd;

import pl.harpi.samples.j2ee.demo.api.base.service.Command;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.exceptions.PersonNotFoundException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.domain.model.service.PersonBeanContext;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

public class GetPersonByIdCmd implements Command<PersonDTO, PersonBeanContext> {
    private Long personId;

    public GetPersonByIdCmd(Long personId) {
        this.personId = personId;
    }

    @Override
    public PersonDTO execute(PersonBeanContext commandContext) throws ApplicationException {
        Person person = commandContext.getRepository().load(personId);

        if (person == null) {
            throw new PersonNotFoundException(personId);
        }

        return person.createDTO();
    }
}
