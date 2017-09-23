package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.api.PersonSearchVO;

public interface PersonLocal {
    PersonDTO savePerson(PersonDTO person) throws ApplicationException;

    PersonDTO getPersonById(Long personId) throws ApplicationException;

    DataResult getPersons(PersonSearchVO findVO, QueryProperty sort, OrderType order) throws ApplicationException;

    DataResult getPersons(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) throws ApplicationException;
}
