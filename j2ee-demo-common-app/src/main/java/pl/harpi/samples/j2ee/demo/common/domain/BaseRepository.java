package pl.harpi.samples.j2ee.demo.common.domain;

public interface BaseRepository<E extends BaseEntity> {
    E load(Long id);

    void save(E entity);
}
