package pl.harpi.samples.j2ee.demo.model.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JPABaseRepository<ENTITY extends BaseEntity> implements BaseRepository<ENTITY> {
    @PersistenceContext
    private EntityManager entityManager;
    private final Class<ENTITY> entityClass;

    public JPABaseRepository() {
        entityClass = (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private Class<ENTITY> getEntityClass() {
        return entityClass;
    }

    @Override
    public ENTITY load(Long id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public void save(ENTITY entity) {
        if (entity.getId() == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }
}
