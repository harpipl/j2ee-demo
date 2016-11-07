package pl.harpi.samples.j2ee.demo.domain.model;

import pl.harpi.samples.j2ee.demo.domain.base.model.MutablePersistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person extends MutablePersistentEntity {
    @Column(name = "FIRST_NAME", length = 240, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 240, nullable = false)
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
