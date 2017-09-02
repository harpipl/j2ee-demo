package pl.harpi.samples.j2ee.demo.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.model.base.JPABaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

import javax.ejb.Stateless;

@Stateless
public class JPAPersonRepository extends JPABaseRepository<Person> implements PersonRepository {
    @Override
    public JPAPersonQuery createPersonQuery() {
        return new JPAPersonQuery(getEntityManager());
    }
}
