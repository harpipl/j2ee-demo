package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.api.base.model.DataResult;
import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;
import pl.harpi.samples.j2ee.demo.api.model.PersonDTO;
import pl.harpi.samples.j2ee.demo.service.rest.RestResponseFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PersonCollectionResponse {
    private long start;
    private long size;
    private QueryProperty sort;
    private OrderType order;
    private long total;
    private List<PersonResponse> data;

    public PersonCollectionResponse(DataResult dataResult, HttpServletRequest request) {
        List<PersonResponse> responseList = new ArrayList<>();

        for (Object person : ((List<Object>) dataResult.getData())) {
            responseList.add(RestResponseFactory.createPersonResponse((PersonDTO) person, request));
        }

        this.start = dataResult.getStart();
        this.size = dataResult.getSize();
        this.sort = dataResult.getSort();
        this.order = dataResult.getOrder();
        this.total = dataResult.getTotal();
        this.data = responseList;
    }

    public List<PersonResponse> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<PersonResponse> data) {
        this.data = data;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public QueryProperty getSort() {
        return sort;
    }

    public void setSort(QueryProperty sort) {
        this.sort = sort;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
