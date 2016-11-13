package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.api.base.model.AddressType;
import pl.harpi.samples.j2ee.demo.api.base.model.IAddress;
import pl.harpi.samples.j2ee.demo.api.base.model.IPerson;
import pl.harpi.samples.j2ee.demo.domain.model.Person;

public class AddressResponse {
    private Long id;
    private AddressType type;
    private String city;
    private String street;
    private String postalCode;
    private String url;

    public AddressResponse(IAddress address, String url) {
        setId(address.getId());
        setCity(address.getCity());
        setType(address.getType());
        setPostalCode(address.getPostalCode());
        setStreet(address.getStreet());
        setUrl(url);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AddressType getType() {
        return type;
    }
    public void setType(AddressType type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
