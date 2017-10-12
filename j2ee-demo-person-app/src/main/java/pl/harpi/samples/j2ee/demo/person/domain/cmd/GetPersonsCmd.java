package pl.harpi.samples.j2ee.demo.person.domain.cmd;

import pl.harpi.samples.j2ee.demo.common.api.DataResult;
import pl.harpi.samples.j2ee.demo.common.api.OrderType;
import pl.harpi.samples.j2ee.demo.common.api.QueryProperty;
import pl.harpi.samples.j2ee.demo.common.domain.ApplicationException;
import pl.harpi.samples.j2ee.demo.common.domain.Command;
import pl.harpi.samples.j2ee.demo.person.api.PersonDTO;
import pl.harpi.samples.j2ee.demo.person.api.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.person.domain.Person;
import pl.harpi.samples.j2ee.demo.person.domain.PersonBeanContext;

import java.util.ArrayList;
import java.util.List;

public class GetPersonsCmd implements Command<DataResult, PersonBeanContext> {
    private PersonSearchVO findVO;
    private int start;
    private int size;
    private QueryProperty sort;
    private OrderType order;

    public GetPersonsCmd(PersonSearchVO findVO, int start, int size, QueryProperty sort, OrderType order) {
        this.findVO = findVO;
        this.start = start;
        this.size = size;
        this.sort = sort;
        this.order = order;
    }

    @Override
    public DataResult execute(PersonBeanContext commandContext) throws ApplicationException {
        List<PersonDTO> personDTOs = new ArrayList<>();

        DataResult result = null;
        DataResult search = commandContext.getRepository().createPersonQuery().searchPage(findVO, start, size, sort, order);
        List objects = null;
        if (search != null && search.getData() != null) {
            List<Object> persons = (List<Object>) search.getData();
            persons.forEach(p -> personDTOs.add(((Person) p).createDTO()));
            objects = personDTOs;
            result = new DataResult(search.getStart(), search.getSize(), search.getSort(), search.getOrder(), search.getTotal(), objects);
        } else {
            objects = personDTOs;
            result = new DataResult(0, 0, sort, order, 0, objects);
        }

        return result;
    }
}
