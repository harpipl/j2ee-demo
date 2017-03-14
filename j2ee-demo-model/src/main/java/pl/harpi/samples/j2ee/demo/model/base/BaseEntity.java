package pl.harpi.samples.j2ee.demo.model.base;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column(name="ID", insertable = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    @Column(name="VER", insertable = true, updatable = true, nullable = false)
    private Integer version;

    protected BaseEntity() {}

    protected BaseEntity(Long id, Integer version) {
        this.setId(id);
        this.setVersion(version);
    }

    public void validate(ValidationNotificationHandler handler) {};

    public Long getId() {
        return id;
    }
    protected void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }
    protected void setVersion(Integer version) {
        this.version = version;
    }
}
