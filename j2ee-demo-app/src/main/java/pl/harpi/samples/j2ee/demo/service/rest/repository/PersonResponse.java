package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;

public class PersonResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String url;

    public PersonResponse(PersonDTO person, String url) {
        setId(person.getId());
        setFirstName(person.getFirstName());
        setLastName(person.getLastName());
        setUrl(url);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}