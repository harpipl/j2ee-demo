package pl.harpi.samples.j2ee.demo.domain.model.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public class JPABaseRepository<ENTITY extends BaseEntity> implements BaseRepository<ENTITY> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected final Class<ENTITY> entityClass;

    public JPABaseRepository() {
        entityClass = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public ENTITY load(Long id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void save(ENTITY entity) {
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }
}
