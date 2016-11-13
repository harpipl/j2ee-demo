package pl.harpi.samples.j2ee.demo.domain.base.dao;

import pl.harpi.samples.j2ee.demo.api.base.types.dao.IBaseDAO;
import pl.harpi.samples.j2ee.demo.api.base.types.model.Persistable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class BaseDAO<C extends T, T extends Persistable<ID>, ID extends Serializable> implements IBaseDAO<T, ID> {
    @PersistenceContext
    private EntityManager entityManager;
    private final Class<C> entityClass;

    public BaseDAO() {
        this.entityClass = null;
    }

    public BaseDAO(Class<C> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T saveOrUpdate(T t) {
        if (t.getId() == null) {
            entityManager.persist(t);
        } else {
            entityManager.merge(t);
        }
        return t;
    }

    @Override
    public void delete(T t) {
        this.entityManager.remove(t);
    }

    @Override
    public void deleteById(ID id) {
        Object ref = this.entityManager.getReference(entityClass, id);
        this.entityManager.remove(ref);
    }

    @Override
    public T find(ID id) {
        return (T) this.entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<C> cq = cb.createQuery(entityClass);
        Root<C> rootEntry = cq.from(entityClass);
        CriteriaQuery<C> all = cq.select(rootEntry);
        TypedQuery<C> allQuery = entityManager.createQuery(all);
        return (List<T>) allQuery.getResultList();
    }
}
