package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.api.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.common.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.person.domain.cmd.CreatePersonCmd;
import pl.harpi.samples.j2ee.demo.person.domain.cmd.GetPersonByIdCmd;
import pl.harpi.samples.j2ee.demo.person.domain.cmd.GetPersonsCmd;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Local
@Stateless
public class PersonBean {
    @Inject
    private PersonRepository repository;

    private PersonBeanContext getPersonBeanContext() {
        return new PersonBeanContext(repository);
    }

    public PersonDTO savePerson(PersonDTO personDTO) throws ApplicationException {
        return new CreatePersonCmd(personDTO).execute(getPersonBeanContext());
    }

    public PersonDTO getPersonById(Long personId) throws ApplicationException {
        return new GetPersonByIdCmd(personId).execute(getPersonBeanContext());
    }

    public DataResult getPersons(PersonSearchVO findVO, QueryProperty sort, OrderType order) throws ApplicationException {
        return getPersons(findVO, 0, ResourceConstants.INFINITE_MAX_RESULT_SIZE, sort, order);
    }

    public DataResult getPersons(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) throws ApplicationException {
        return new GetPersonsCmd(findVO, start, size, sort, order).execute(getPersonBeanContext());
    }
}
