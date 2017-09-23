package pl.harpi.samples.j2ee.demo.common.domain;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.Query;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.common.rest.ResourceConstants;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseQuery<E extends Serializable, S> implements Query<E, S> {
    private EntityManager entityManager;

    public BaseQuery(EntityManager entityManager) {
        this.setEntityManager(entityManager);
    }

    @Override
    public DataResult search(S findVO, QueryProperty sort, OrderType order) {
        return searchPage(findVO, 0, ResourceConstants.INFINITE_MAX_RESULT_SIZE, sort, order);
    }

    @Override
    public DataResult searchIdsPage(S findVO, int start, int size, OrderType order) {
        return null;
    }

    @Override
    public E singleResult() {
        return null;
    }

    @Override
    public DataResult searchIds(S findVO, OrderType order) {
        DataResult docList = search(findVO, null, order);
        List<Object> list = new ArrayList<>();

        List<Object> objectList = (List<Object>) (docList.getData());

        for (Object o : objectList) {
            Long id = ((BaseEntity) o).getId();
            list.add(id);
        }
        return new DataResult(0L, list.size(), null, OrderType.ASC, list.size(), list);
    }

    @Override
    public DataResult searchPage(S findVO, int start, int size, QueryProperty sort, OrderType order) {
        javax.persistence.Query query = prepareQuery(findVO, start, size, sort, order, false);

        List<Object> results = query.getResultList();

        long total = countTotal(findVO, start, size);
        return new DataResult(0L, results.size(), sort, order, total, results);
    }

    protected abstract javax.persistence.Query prepareQuery(S findVO, int start, int size, QueryProperty sort, OrderType order, boolean count);

    private long countTotal(S findVO, int start, int size) {
        javax.persistence.Query query = prepareQuery(findVO, start, size, null, OrderType.ASC, true);

        Long total = (Long) query.getSingleResult();
        if (total == null) total = 0L;

        return total;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
