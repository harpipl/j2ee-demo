package pl.harpi.samples.j2ee.demo.api.model;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;

public interface PersonLocal {
    PersonDTO savePerson(PersonDTO person) throws ApplicationException;

    PersonDTO getPersonById(Long personId) throws ApplicationException;

    DataResult getPersons(PersonSearchVO findVO, QueryProperty sort, OrderType order) throws ApplicationException;

    DataResult getPersons(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) throws ApplicationException;
}
