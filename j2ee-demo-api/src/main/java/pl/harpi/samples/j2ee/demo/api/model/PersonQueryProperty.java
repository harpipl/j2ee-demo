package pl.harpi.samples.j2ee.demo.api.model;

import pl.harpi.samples.j2ee.demo.api.base.model.OrderType;
import pl.harpi.samples.j2ee.demo.api.base.model.QueryProperty;

import java.util.HashMap;
import java.util.Map;

public class PersonQueryProperty implements QueryProperty {
    private static final Map<String, PersonQueryProperty> properties = new HashMap<>();

    public static final PersonQueryProperty FIRST_NAME = new PersonQueryProperty("firstName");
    public static final PersonQueryProperty LAST_NAME = new PersonQueryProperty("lastName");

    public static final PersonQueryProperty SORT_BY_DEFAULT = LAST_NAME;
    public static final OrderType ORDER_BY_DEFAULT = OrderType.ASC;

    private String name;

    public PersonQueryProperty(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static PersonQueryProperty findByName(String propertyName) {
        return properties.get(propertyName);
    }
}
