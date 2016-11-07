package pl.harpi.samples.j2ee.demo.domain.dao;

import pl.harpi.samples.j2ee.demo.domain.base.dao.BaseDAO;
import pl.harpi.samples.j2ee.demo.domain.model.Person;

import javax.ejb.Stateless;

@Stateless
public class PersonDAO extends BaseDAO<Person, Long> {
    public PersonDAO() {
        super(Person.class);
    }
}
