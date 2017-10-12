package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.domain.BaseEntity;
import pl.harpi.samples.j2ee.demo.common.domain.DTOCreatable;
import pl.harpi.samples.j2ee.demo.common.domain.ValidationNotificationHandler;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTOBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PERSON")
public class Person extends BaseEntity implements Serializable, DTOCreatable<PersonDTO> {
    @Column(name = "FIRST_NAME", length = 240, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 240, nullable = false)
    private String lastName;

    protected Person() {
    }

    public Person(PersonDTO dto) {
        super(dto.getId(), null);
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
    }

    @Override
    public PersonDTO createDTO() {
        return new PersonDTOBuilder()
                .withId(getId())
                .withFirstName(getFirstName())
                .withLastName(getLastName())
                .build();
    }

    @Override
    public void validate(ValidationNotificationHandler handler) {
        new PersonValidator(this, handler).validate();
    }

    String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
