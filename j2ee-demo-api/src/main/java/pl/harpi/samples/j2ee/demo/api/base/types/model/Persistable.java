package pl.harpi.samples.j2ee.demo.api.base.types.model;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable {
    ID getId();
}

