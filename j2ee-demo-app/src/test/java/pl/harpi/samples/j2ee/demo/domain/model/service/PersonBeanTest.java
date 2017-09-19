package pl.harpi.samples.j2ee.demo.domain.model.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonQueryProperty;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;
import pl.harpi.samples.j2ee.demo.model.repository.jpa.JPAPersonQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonBeanTest {
    @InjectMocks
    private PersonBean personService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private PersonRepository repository;

    @Test
    public void search_withAllPersons_shouldReturnFourPersons() throws ApplicationException {
        // given
        Query query = Mockito.mock(Query.class);

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(repository.createPersonQuery()).thenReturn(new JPAPersonQuery(entityManager));

        // when
        DataResult persons = personService.getPersons(new PersonSearchVO(), PersonQueryProperty.SORT_BY_DEFAULT, PersonQueryProperty.ORDER_BY_DEFAULT);

        // then
        verify(repository, times(1)).createPersonQuery();

        assertThat(persons).isNotNull();
        assertThat(persons.getData()).isNotNull();
        assertThat(((List<Object>) (persons.getData())).size()).isEqualTo(0);
    }

    @Test
    public void search_withStartTwoAndSizeOne_shouldReturnOnlyDavid() throws ApplicationException {
        // given
        Query query = Mockito.mock(Query.class);

        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(repository.createPersonQuery()).thenReturn(new JPAPersonQuery(entityManager));

        // when
        DataResult persons = personService.getPersons(new PersonSearchVO(), 2, 1, PersonQueryProperty.SORT_BY_DEFAULT, PersonQueryProperty.ORDER_BY_DEFAULT);

        // then
        verify(repository, times(1)).createPersonQuery();
    }
}
