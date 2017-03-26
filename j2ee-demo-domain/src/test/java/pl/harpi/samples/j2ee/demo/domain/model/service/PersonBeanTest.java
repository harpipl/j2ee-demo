package pl.harpi.samples.j2ee.demo.domain.model.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.model.base.BaseRepository;
import pl.harpi.samples.j2ee.demo.model.repository.PersonRepository;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonBeanTest {
    @InjectMocks
    private PersonBean personService;

    @Mock
    private PersonRepository repository;

    @Test
    public void search_withAllPersons_shouldReturnFourPersons() {
        // given

        // when
        List<PersonDTO> persons = personService.getPersons(new PersonSearchVO());

        // then
        verify(repository, times(1)).searchPage(any(), eq(0), eq(BaseRepository.INFINITE_MAX_RESULT_SIZE));

        assertThat(persons).isNotNull();
        assertThat(persons.size()).isEqualTo(0);
    }

    @Test
    public void search_withStartTwoAndSizeOne_shouldReturnOnlyDavid() {
        // given

        // when
        List<PersonDTO> persons = personService.getPersons(new PersonSearchVO(), 2, 1);

        // then
        verify(repository, times(1)).searchPage(any(), eq(2), eq(1));
    }
}
