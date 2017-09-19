package pl.harpi.samples.j2ee.demo.domain.model.service;

import pl.harpi.samples.j2ee.demo.base.service.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.exceptions.PersonNotFoundException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonLocal;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.domain.model.base.MessagesValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.model.base.ValidationNotificationHandler;
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
        ValidationNotificationHandler handler = new MessagesValidationNotificationHandler();
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
    public DataResult getPersons(PersonSearchVO findVO, QueryProperty sort, OrderType order) {
        return getPersons(findVO, 0, ResourceConstants.INFINITE_MAX_RESULT_SIZE, sort, order);
    }

    @Override
    public DataResult getPersons(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) {
        List<PersonDTO> personDTOs = new ArrayList<>();

        DataResult result = null;
        DataResult search = repository.createPersonQuery().searchPage(findVO, start, size, sort, order);
        List objects = null;
        if (search != null && search.getData() != null) {
            List<Object> persons = (List<Object>) search.getData();
            persons.forEach(p -> personDTOs.add(((Person) p).createDTO()));
            objects = personDTOs;
            result = new DataResult(search.getStart(), search.getSize(), search.getSort(), search.getOrder(), search.getTotal(), objects);
        } else {
            objects = personDTOs;
            result = new DataResult(0, 0, sort, order, 0, objects);
        }

        return result;

    }
}
