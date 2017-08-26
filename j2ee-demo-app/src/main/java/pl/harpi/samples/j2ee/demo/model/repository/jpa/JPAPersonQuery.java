package pl.harpi.samples.j2ee.demo.model.repository.jpa;

import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.Query;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseQuery;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

import javax.persistence.EntityManager;

public class JPAPersonQuery extends BaseQuery<Person, PersonSearchVO> implements Query<Person, PersonSearchVO> {
    public JPAPersonQuery(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected javax.persistence.Query prepareQuery(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order, boolean count) {
        StringBuilder sql = new StringBuilder();

        String what = (count) ? "count(p)" : "p";

        sql.append("SELECT ").append(what).append(" FROM Person p WHERE 1=1 ");

        if (findVO.getFirstName() != null) {
            sql.append(" AND firstName = :firstName ");
        }

        if (findVO.getLastName() != null) {
            sql.append(" AND lastName = :lastName ");
        }

        if (!count && sort != null) {
            sql.append(" ORDER BY ").append(sort.getName());
            if (order == OrderType.ASC) {
                sql.append(" ").append(OrderType.ASC.name());
            } else {
                sql.append(" ").append(OrderType.DESC.name());
            }
        }

        javax.persistence.Query query = getEntityManager().createQuery(sql.toString());

        if (findVO.getFirstName() != null) {
            query.setParameter("firstName", findVO.getFirstName());
        }

        if (findVO.getLastName() != null) {
            query.setParameter("lastName", findVO.getLastName());
        }

        if (!count) {
            query.setFirstResult(start);
            if (BaseRepository.INFINITE_MAX_RESULT_SIZE != size) {
                query.setMaxResults(size);
            }
        }

        return query;
    }
}
