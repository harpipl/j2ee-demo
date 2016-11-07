package pl.harpi.samples.j2ee.demo.domain.base.model;

import org.hibernate.annotations.Immutable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Immutable
public abstract class ImmutablePersistentEntity extends PersistentEntity<Long> {
    private static final long serialVersionUID = 1L;

    public ImmutablePersistentEntity() {
    }
}