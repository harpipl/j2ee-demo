package pl.harpi.samples.j2ee.demo.domain.model;

import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.domain.base.model.MutablePersistentEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class Person extends MutablePersistentEntity implements IPerson {
    @Column(name = "FIRST_NAME", length = 240, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 240, nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public List<IAddress> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        return (List<IAddress>)(List<?>) addresses;
    }
}
