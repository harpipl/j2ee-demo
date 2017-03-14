package pl.harpi.samples.j2ee.demo.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;
import pl.harpi.samples.j2ee.demo.model.base.JPABaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class JPAPersonRepository extends JPABaseRepository<Person> implements PersonRepository {
    @Override
    public List<Person> search(PersonSearchVO findVO) {
        // TODO
        return null;
    }

    @Override
    public List<Long> searchIds(PersonSearchVO findVO) {
        // TODO
        return null;
    }
}
