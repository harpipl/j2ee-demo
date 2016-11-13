package pl.harpi.samples.j2ee.demo.service.rest.repository;

import java.util.ArrayList;
import java.util.List;

public class PersonRequest {
    private String firstName;
    private String lastName;
    private List<AddressRequest> addresses;

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

    public List<AddressRequest> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
}
