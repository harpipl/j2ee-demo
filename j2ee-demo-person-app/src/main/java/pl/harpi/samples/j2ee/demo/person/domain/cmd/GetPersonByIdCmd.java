package pl.harpi.samples.j2ee.demo.person.domain.cmd;

import pl.harpi.samples.j2ee.demo.common.domain.Command;
import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.person.domain.PersonNotFoundException;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.domain.PersonBeanContext;
import pl.harpi.samples.j2ee.demo.person.domain.Person;

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
