package pl.harpi.j2ee.demo.api.base.dao;

import pl.harpi.j2ee.demo.api.base.model.Persistable;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO<T extends Persistable<ID>, ID extends Serializable> {
    T saveOrUpdate(T t);

    void delete(T t);
    void deleteById(ID id);

    T find(ID id);
    List<T> findAll();
}
