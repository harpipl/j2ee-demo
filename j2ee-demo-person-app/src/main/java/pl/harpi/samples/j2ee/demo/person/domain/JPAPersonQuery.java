package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.Query;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.person.api.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.common.rest.ResourceConstants;
import pl.harpi.samples.j2ee.demo.common.domain.BaseQuery;

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
            if (ResourceConstants.INFINITE_MAX_RESULT_SIZE != size) {
                query.setMaxResults(size);
            }
        }

        return query;
    }
}
