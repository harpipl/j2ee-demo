package pl.harpi.samples.j2ee.demo.model.base;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.Query;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.model.PersonQueryProperty;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static pl.harpi.samples.j2ee.demo.model.base.BaseRepository.INFINITE_MAX_RESULT_SIZE;

public abstract class BaseQuery<ENTITY extends Serializable, SEARCH> implements Query<ENTITY, SEARCH> {
    private EntityManager entityManager;

    @Override
    public DataResult search(SEARCH findVO, QueryProperty sort, OrderType order) {
        return searchPage(findVO, 0, INFINITE_MAX_RESULT_SIZE, sort, order);
    }

    @Override
    public DataResult searchIdsPage(SEARCH findVO, int start, int size, OrderType order) {
        return null;
    }

    @Override
    public ENTITY singleResult() {
        return null;
    }

    @Override
    public DataResult searchIds(SEARCH findVO, OrderType order) {
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
    public DataResult searchPage(SEARCH findVO, int start, int size, QueryProperty sort, OrderType order) {
        javax.persistence.Query query = prepareQuery(findVO, start, size, sort, order, false);

        List<Object> results = query.getResultList();

        long total = countTotal(findVO, start, size);
        return new DataResult(0L, results.size(), sort, order, total, results);
    }

    protected abstract javax.persistence.Query prepareQuery(SEARCH findVO, int start, int size, QueryProperty sort, OrderType order, boolean count);

    private long countTotal(SEARCH findVO, int start, int size) {
        javax.persistence.Query query = prepareQuery(findVO, start, size, PersonQueryProperty.SORT_BY_DEFAULT, PersonQueryProperty.ORDER_BY_DEFAULT, true);

        Long total = (Long) query.getSingleResult();
        if (total == null) total = 0L;

        return total;
    }
}
