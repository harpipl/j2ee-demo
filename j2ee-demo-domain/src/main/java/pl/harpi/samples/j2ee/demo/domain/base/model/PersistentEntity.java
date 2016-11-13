package pl.harpi.samples.j2ee.demo.domain.base.model;

import pl.harpi.samples.j2ee.demo.api.base.types.model.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistentEntity<ID extends Serializable> implements Persistable<ID> {
    private static final long serialVersionUID = 1L;
    public static final String _ID = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private ID id;

    public PersistentEntity() {
    }

    public ID getId() {
        return this.id;
    }

    protected void setId(ID id) {
        this.id = id;
    }

    protected Object clone() throws CloneNotSupportedException {
        PersistentEntity clone = PersistentEntity.class.cast(super.clone());
        clone.id = null;
        return clone;
    }
}
