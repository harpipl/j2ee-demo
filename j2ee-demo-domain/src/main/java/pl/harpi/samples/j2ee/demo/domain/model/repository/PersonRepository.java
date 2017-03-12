package pl.harpi.samples.j2ee.demo.domain.model.repository;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.domain.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.domain.model.entity.Person;

import java.util.List;

public interface PersonRepository extends BaseRepository<Person> {
    List<Person> search(PersonSearchVO findVO);
    List<Long> searchIds(PersonSearchVO findVO);
}
