package pl.harpi.samples.j2ee.demo.domain.model.service;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonLocal;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.base.service.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.domain.model.cmd.CreatePersonCmd;
import pl.harpi.samples.j2ee.demo.domain.model.cmd.GetPersonByIdCmd;
import pl.harpi.samples.j2ee.demo.domain.model.cmd.GetPersonsCmd;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PersonBean implements PersonLocal {
    @Inject
    private PersonRepository repository;

    private PersonBeanContext getPersonBeanContext() {
        return new PersonBeanContext(repository);
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) throws ApplicationException {
        return new CreatePersonCmd(personDTO).execute(getPersonBeanContext());
    }

    @Override
    public PersonDTO getPersonById(Long personId) throws ApplicationException {
        return new GetPersonByIdCmd(personId).execute(getPersonBeanContext());
    }

    @Override
    public DataResult getPersons(PersonSearchVO findVO, QueryProperty sort, OrderType order) throws ApplicationException {
        return getPersons(findVO, 0, ResourceConstants.INFINITE_MAX_RESULT_SIZE, sort, order);
    }

    @Override
    public DataResult getPersons(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) throws ApplicationException {
        return new GetPersonsCmd(findVO, start, size, sort, order).execute(getPersonBeanContext());
    }
}
