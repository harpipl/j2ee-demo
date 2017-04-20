package pl.harpi.samples.j2ee.demo.model.base;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;

public interface BaseRepository<ENTITY extends BaseEntity, SEARCH> {
    int INFINITE_MAX_RESULT_SIZE = -1;

    ENTITY load(Long id);

    void save(ENTITY entity);
}
