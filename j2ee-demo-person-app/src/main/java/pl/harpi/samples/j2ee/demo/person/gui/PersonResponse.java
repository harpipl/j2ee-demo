package pl.harpi.samples.j2ee.demo.person.gui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkad on 10.05.2017.
 */
public class PersonResponse {
    private Long start;
    private Long size;
    private Sort sort;
    private String order;
    private Long total;
    private List<Person> data;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Person> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
