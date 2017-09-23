package pl.harpi.samples.j2ee.demo.common.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JPABaseRepository<E extends BaseEntity> implements BaseRepository<E> {
    @PersistenceContext
    private EntityManager entityManager;
    private final Class<E> entityClass;

    public JPABaseRepository() {
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    private Class<E> getEntityClass() {
        return entityClass;
    }

    @Override
    public E load(Long id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    @Override
    public void save(E entity) {
        if (entity.getId() == null) {
            getEntityManager().persist(entity);
        } else {
            getEntityManager().merge(entity);
        }
    }
}
