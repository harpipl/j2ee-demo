package pl.harpi.samples.j2ee.demo.api.model;

import pl.harpi.samples.j2ee.demo.api.base.model.BaseDTO;

public class PersonDTO extends BaseDTO {
    private String firstName;
    private String lastName;

    public PersonDTO(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
