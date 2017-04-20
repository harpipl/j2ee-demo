package pl.harpi.samples.j2ee.demo.api.base.model;

import java.io.Serializable;

public interface Query<ENTITY extends Serializable, SEARCH> {
    DataResult search(SEARCH findVO, QueryProperty sort, OrderType order);

    DataResult searchIds(SEARCH findVO, OrderType order);

    DataResult searchPage(SEARCH findVO, int start, int size, QueryProperty sort, OrderType order);

    DataResult searchIdsPage(SEARCH findVO, int start, int size, OrderType order);

    ENTITY singleResult();
}
