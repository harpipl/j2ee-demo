package pl.harpi.samples.j2ee.demo.model.base;

public interface BaseRepository<E extends BaseEntity> {
    E load(Long id);

    void save(E entity);
}
