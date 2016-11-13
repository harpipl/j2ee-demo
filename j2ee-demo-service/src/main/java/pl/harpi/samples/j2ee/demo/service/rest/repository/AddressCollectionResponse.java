package pl.harpi.samples.j2ee.demo.service.rest.repository;

import pl.harpi.samples.j2ee.demo.domain.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressCollectionResponse {
    public AddressCollectionResponse(List<AddressResponse> data) {
        this.data = data;
    }

    private List<AddressResponse> data;

    public List<AddressResponse> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<AddressResponse> data) {
        this.data = data;
    }
}
