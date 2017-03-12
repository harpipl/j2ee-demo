package pl.harpi.samples.j2ee.demo.domain.model.base;

import pl.harpi.samples.j2ee.demo.domain.model.base.BaseEntity;

public interface BaseRepository<ENTITY extends BaseEntity> {
    ENTITY load(Long id);
    void save(ENTITY entity);
}
