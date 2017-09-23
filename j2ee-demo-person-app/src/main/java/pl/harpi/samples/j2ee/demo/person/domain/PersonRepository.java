package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.api.Query;
import pl.harpi.samples.j2ee.demo.common.domain.BaseRepository;

public interface PersonRepository extends BaseRepository<Person> {
        Query createPersonQuery();
}
