package pl.harpi.samples.j2ee.demo.common.api;

import java.io.Serializable;

public interface Query<E extends Serializable, S> {
    DataResult search(S findVO, QueryProperty sort, OrderType order);

    DataResult searchIds(S findVO, OrderType order);

    DataResult searchPage(S findVO, int start, int size, QueryProperty sort, OrderType order);

    DataResult searchIdsPage(S findVO, int start, int size, OrderType order);

    E singleResult();
}
