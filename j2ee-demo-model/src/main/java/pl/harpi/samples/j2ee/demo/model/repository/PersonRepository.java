package pl.harpi.samples.j2ee.demo.model.repository;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

import java.util.List;

public interface PersonRepository extends BaseRepository<Person> {
    List<Person> search(PersonSearchVO findVO);
    List<Long> searchIds(PersonSearchVO findVO);
}
