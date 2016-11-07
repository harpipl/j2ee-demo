package pl.harpi.j2ee.demo.api.base.model;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable {
    ID getId();
}

