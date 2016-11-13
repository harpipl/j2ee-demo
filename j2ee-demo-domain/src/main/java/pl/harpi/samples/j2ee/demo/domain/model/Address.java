package pl.harpi.samples.j2ee.demo.domain.model;

import pl.harpi.samples.j2ee.demo.api.base.model.AddressType;
import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.domain.base.model.MutablePersistentEntity;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address extends MutablePersistentEntity implements IAddress {

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 120, nullable = false)
    private AddressType type;

    @Column(name = "CITY", length = 240, nullable = false)
    private String city;

    @Column(name = "STREET", length = 240, nullable = false)
    private String street;

    @Column(name = "POSTAL_CODE", length = 20, nullable = false)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    private Person person;

    @Override
    public AddressType getType() {
        return type;
    }
    @Override
    public void setType(AddressType type) {
        this.type = type;
    }

    @Override
    public String getCity() {
        return city;
    }
    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getStreet() {
        return street;
    }
    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }
    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public IPerson getPerson() {
        return person;
    }

    public void setPerson(IPerson person) {
        this.person = (Person) person;
    }
}
