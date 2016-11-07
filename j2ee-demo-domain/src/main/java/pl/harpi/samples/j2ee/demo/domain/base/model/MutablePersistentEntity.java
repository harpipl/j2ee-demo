package pl.harpi.samples.j2ee.demo.domain.base.model;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Entity(
        optimisticLock = OptimisticLockType.VERSION
)
public abstract class MutablePersistentEntity extends PersistentEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;

    public MutablePersistentEntity() {
    }

    public Integer getVersion() {
        return this.version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }

    protected Object clone() throws CloneNotSupportedException {
        MutablePersistentEntity clone = MutablePersistentEntity.class.cast(super.clone());
        clone.version = null;
        return clone;
    }
}
