package pl.harpi.samples.j2ee.demo.api.model;

public class PersonDTOBuilder {
    private Long id;
    private String firstName;
    private String lastName;

    public PersonDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PersonDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonDTO build() {
        return new PersonDTO(id, firstName, lastName);
    }

    public static PersonDTOBuilder defaultPersonDTOBuilder() {
        return new PersonDTOBuilder().withId(1L).withFirstName("John").withLastName("Smith");
    }

    public static PersonDTOBuilder emptyPersonDTOBuilder() {
        return new PersonDTOBuilder();
    }
}
