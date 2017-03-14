package pl.harpi.samples.j2ee.demo.model.base;

public interface BaseRepository<ENTITY extends BaseEntity> {
    ENTITY load(Long id);
    void save(ENTITY entity);
}
