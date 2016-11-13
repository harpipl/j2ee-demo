package pl.harpi.samples.j2ee.demo.api.base.model;

import pl.harpi.samples.j2ee.demo.api.base.types.model.Persistable;

public interface IAddress extends Persistable<Long> {
    AddressType getType();

    void setType(AddressType type);

    String getCity();

    void setCity(String city);

    String getStreet();

    void setStreet(String street);

    String getPostalCode();

    void setPostalCode(String postalCode);

    public IPerson getPerson();
    public void setPerson(IPerson person);
}