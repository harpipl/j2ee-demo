package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.domain.JPABaseRepository;

import javax.ejb.Stateless;

@Stateless
public class JPAPersonRepository extends JPABaseRepository<Person> implements PersonRepository {
    @Override
    public JPAPersonQuery createPersonQuery() {
        return new JPAPersonQuery(getEntityManager());
    }
}
