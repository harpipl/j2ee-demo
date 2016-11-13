package pl.harpi.samples.j2ee.demo.api.base.types.dao;

import pl.harpi.samples.j2ee.demo.api.base.types.model.Persistable;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO<T extends Persistable<ID>, ID extends Serializable> {
    T saveOrUpdate(T t);

    void delete(T t);
    void deleteById(ID id);

    T find(ID id);
    List<T> findAll();
}
