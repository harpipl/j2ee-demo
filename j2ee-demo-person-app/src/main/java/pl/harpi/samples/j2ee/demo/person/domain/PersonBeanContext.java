package pl.harpi.samples.j2ee.demo.person.domain;

import pl.harpi.samples.j2ee.demo.common.domain.CommandContext;

public class PersonBeanContext implements CommandContext {
    private PersonRepository repository;

    public PersonBeanContext(PersonRepository repository){
        this.repository = repository;
    }

    public PersonRepository getRepository() {
        return repository;
    }
}
