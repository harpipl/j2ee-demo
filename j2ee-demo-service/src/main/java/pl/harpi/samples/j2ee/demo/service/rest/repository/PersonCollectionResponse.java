package pl.harpi.samples.j2ee.demo.service.rest.repository;

import java.util.ArrayList;
import java.util.List;

public class PersonCollectionResponse {
    public PersonCollectionResponse(List<PersonResponse> data) {
        this.data = data;
    }

    private List<PersonResponse> data;

    public List<PersonResponse> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<PersonResponse> data) {
        this.data = data;
    }
}
