package pl.harpi.samples.j2ee.demo.api.base.model;

import pl.harpi.samples.j2ee.demo.api.base.types.model.Persistable;

import java.util.List;

public interface IPerson extends Persistable<Long> {

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    List<IAddress> getAddresses();
}
