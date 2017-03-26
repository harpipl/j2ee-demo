package pl.harpi.samples.j2ee.demo.model.repository;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

public interface PersonRepository extends BaseRepository<Person, PersonSearchVO> {
}
