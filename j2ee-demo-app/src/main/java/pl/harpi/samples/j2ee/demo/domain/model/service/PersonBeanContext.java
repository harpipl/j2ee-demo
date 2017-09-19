package pl.harpi.samples.j2ee.demo.domain.model.service;

import pl.harpi.samples.j2ee.demo.api.base.service.CommandContext;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

public class PersonBeanContext implements CommandContext {
    private PersonRepository repository;

    public PersonBeanContext(PersonRepository repository){
        this.repository = repository;
    }

    public PersonRepository getRepository() {
        return repository;
    }
}
