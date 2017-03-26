package pl.harpi.samples.j2ee.demo.domain.model.service;

import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.exceptions.PersonNotFoundException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonLocal;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.domain.model.base.MessagesValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.base.DataResult;
import pl.harpi.samples.j2ee.demo.model.entity.Person;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonBean implements PersonLocal {
    @Inject
    private PersonRepository repository;

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) throws ApplicationException {
        MessagesValidationNotificationHandler handler = new MessagesValidationNotificationHandler();
        Person person = new Person(personDTO);

        person.validate(handler);

        if (handler.hasErrors()) {
            throw new ApplicationException(handler.getMessages());
        } else {
            repository.save(person);
            return person.createDTO();
        }
    }

    @Override
    public PersonDTO getPersonById(Long personId) throws ApplicationException {
        Person person = repository.load(personId);

        if (person == null) {
            throw new PersonNotFoundException(personId);
        }

        return person.createDTO();
    }

    @Override
    public List<PersonDTO> getPersons(PersonSearchVO findVO) {
        return getPersons(findVO,0, BaseRepository.INFINITE_MAX_RESULT_SIZE);
    }

    @Override
    public List<PersonDTO> getPersons(PersonSearchVO findVO, int start, int size) {
        List<PersonDTO> personDTOs = new ArrayList<>();

        DataResult search = repository.searchPage(findVO, start, size);
        if (search != null && search.getData() != null) {
            List<Object> persons = search.getData();
            persons.forEach(p -> personDTOs.add(((Person) p).createDTO()));
        }

        return personDTOs;

    }
}
