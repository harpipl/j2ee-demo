package pl.harpi.samples.j2ee.demo.model.base;

import java.util.List;

public interface BaseRepository<ENTITY extends BaseEntity, SEARCH extends Object> {
    public static int INFINITE_MAX_RESULT_SIZE = -1;

    ENTITY load(Long id);

    void save(ENTITY entity);

    DataResult search(SEARCH findVO);

    DataResult searchIds(SEARCH findVO);

    DataResult searchPage(SEARCH findVO, int firstResult, int maxResults);

    DataResult searchIdsPage(SEARCH findVO, int firstResult, int maxResults);

    long count();

    ENTITY singleResult();
}
