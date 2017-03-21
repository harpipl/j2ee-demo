package pl.harpi.samples.j2ee.demo.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;
import pl.harpi.samples.j2ee.demo.model.base.JPABaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class JPAPersonRepository extends JPABaseRepository<Person> implements PersonRepository {
    @Override
    public List<Person> search(PersonSearchVO findVO) {
        String sql = "SELECT p FROM Person p WHERE 1=1 ";

        if (findVO.getFirstName() != null) {
            sql += " AND firstName = :firstName ";
        }

        if (findVO.getLastName() != null) {
            sql += " AND lastName = :lastName ";
        }

        Query query = this.entityManager.createQuery(sql);

        if (findVO.getFirstName() != null) {
            query.setParameter("firstName", findVO.getFirstName());
        }

        if (findVO.getLastName() != null) {
            query.setParameter("lastName", findVO.getLastName());
        }

        return query.getResultList();
    }

    @Override
    public List<Long> searchIds(PersonSearchVO findVO) {
        List<Person> docList = search(findVO);
        return docList.stream().map(sc -> sc.getId()).collect(Collectors.toList());
    }
}
