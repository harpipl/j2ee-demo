package pl.harpi.samples.j2ee.demo.model.base;

public interface BaseRepository<ENTITY extends BaseEntity> {
    int INFINITE_MAX_RESULT_SIZE = -1;

    ENTITY load(Long id);

    void save(ENTITY entity);
}
