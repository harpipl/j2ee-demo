package pl.harpi.samples.j2ee.demo.domain.model.cmd;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.base.service.Command;
import pl.harpi.samples.j2ee.demo.api.exceptions.ApplicationException;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.api.model.PersonSearchVO;
import pl.harpi.samples.j2ee.demo.domain.model.service.PersonBeanContext;
import pl.harpi.samples.j2ee.demo.model.entity.Person;

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
