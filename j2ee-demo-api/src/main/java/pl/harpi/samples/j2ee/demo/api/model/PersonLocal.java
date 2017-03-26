package pl.harpi.samples.j2ee.demo.api.model;

import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;

import java.util.List;

public interface PersonLocal {
    PersonDTO savePerson(PersonDTO person) throws ApplicationException;
    PersonDTO getPersonById(Long personId) throws ApplicationException;

    List<PersonDTO> getPersons(PersonSearchVO findVO);
    List<PersonDTO> getPersons(PersonSearchVO findVO, int start, int size);
}
