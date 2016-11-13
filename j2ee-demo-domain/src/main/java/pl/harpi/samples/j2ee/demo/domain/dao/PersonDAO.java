package pl.harpi.samples.j2ee.demo.domain.dao;

import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.domain.base.dao.BaseDAO;
import pl.harpi.samples.j2ee.demo.domain.model.Address;
import pl.harpi.samples.j2ee.demo.domain.model.Person;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PersonDAO extends BaseDAO<Person, IPerson, Long> {
    public PersonDAO() {
        super(Person.class);
    }

    public IPerson newPerson() {
        return new Person();
    }

    public IAddress newAddress() { return new Address(); }

    public IPerson findAndFetchAddresses(Long id) {
        IPerson person = super.find(id);

        person.getAddresses().size();

        return person;
    }

    public IAddress saveOrUpdateAddress(Long personId, IAddress address) {
        IPerson person = super.find(personId);

        address.setPerson(person);

        if (address.getId() == null) {
            getEntityManager().persist(address);
        } else {
            getEntityManager().merge(address);
        }

        return address;
    }
}
