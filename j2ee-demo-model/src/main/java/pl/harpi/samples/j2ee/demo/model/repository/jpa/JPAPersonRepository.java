package pl.harpi.samples.j2ee.demo.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseEntity;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.base.DataResult;
import pl.harpi.samples.j2ee.demo.model.base.JPABaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JPAPersonRepository extends JPABaseRepository<Person, PersonSearchVO> implements PersonRepository {
    @Override
    public DataResult searchPage(PersonSearchVO findVO, int firstResult, int maxResults) {
        String sql = "SELECT p FROM Person p WHERE 1=1 ";

        if (findVO.getFirstName() != null) {
            sql += " AND firstName = :firstName ";
        }

        if (findVO.getLastName() != null) {
            sql += " AND lastName = :lastName ";
        }

        Query query = getEntityManager().createQuery(sql);

        if (findVO.getFirstName() != null) {
            query.setParameter("firstName", findVO.getFirstName());
        }

        if (findVO.getLastName() != null) {
            query.setParameter("lastName", findVO.getLastName());
        }

        query.setFirstResult(firstResult);
        if (BaseRepository.INFINITE_MAX_RESULT_SIZE != maxResults) {
            query.setMaxResults(maxResults);
        }

        List<Object> results = query.getResultList();

        return new DataResult(0L, results.size(), results);
    }

    @Override
    public DataResult searchIds(PersonSearchVO findVO) {
        DataResult docList = search(findVO);
        List<Object> list = new ArrayList<>();
        for (Object o : docList.getData()) {
            Long id = ((BaseEntity)o).getId();
            list.add(id);
        }
        return new DataResult(0L, list.size(), list);
    }

    @Override
    public DataResult searchIdsPage(PersonSearchVO findVO, int firstResult, int maxResults) {
        return null;
    }

    @Override
    public DataResult search(PersonSearchVO findVO) {
        return searchPage(findVO, 0, INFINITE_MAX_RESULT_SIZE);
    }

    @Override
    public long count() {
        return searchIds(new PersonSearchVO()).getTotal();
    }

    @Override
    public Person singleResult() {
        return null;
    }
}
