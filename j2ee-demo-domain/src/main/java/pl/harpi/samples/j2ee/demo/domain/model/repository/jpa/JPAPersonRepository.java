package pl.harpi.samples.j2ee.demo.domain.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.domain.model.base.JPABaseRepository;
import pl.harpi.samples.j2ee.demo.domain.model.entity.Person;
import pl.harpi.samples.j2ee.demo.domain.model.repository.PersonRepository;

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
