package pl.harpi.samples.j2ee.demo.model.repository;

import pl.harpi.samples.j2ee.demo.api.base.model.Query;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;
import pl.harpi.samples.j2ee.demo.model.repository.jpa.JPAPersonQuery;

public interface PersonRepository extends BaseRepository<Person, PersonSearchVO> {
    Query createPersonQuery();
}
