package pl.harpi.samples.j2ee.demo.web.main;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import pl.harpi.samples.j2ee.demo.web.domain.Person;
import pl.harpi.samples.j2ee.demo.web.domain.PersonResponse;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arkad on 08.05.2017.
 */
public class MainUI extends UI {
    private Grid<Person> grid = new Grid<>(Person.class);
    private transient List<Person> persons = new ArrayList<>();

    public List<Person> getPersons() {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        return persons;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();

        Button btnNewPerson = new Button("Add new person");
        btnNewPerson.addClickListener(e -> this.addWindow(new WindowModalNewPerson(this)));

        Button btnRefresh = new Button("Refresh");
        btnRefresh.addClickListener(e -> this.updateList());

        HorizontalLayout toolbar = new HorizontalLayout(btnNewPerson, btnRefresh);

        grid = new Grid<>(Person.class);

        grid.setColumns("id", "lastName", "firstName");
        grid.setColumnOrder("id", "lastName", "firstName");
        grid.setSizeFull();

        layout.addComponents(toolbar, grid);

        setContent(layout);
    }

    void updateList() {
        WebTarget targetGetPersons = ClientBuilder.newClient().target("http://localhost:8080/j2ee-demo-app/rest/v1/repository/persons?sort=id");

        PersonResponse response = targetGetPersons.request(MediaType.APPLICATION_JSON_TYPE).get(PersonResponse.class);

        persons.clear();

        if (response != null) {
            persons.addAll(response.getData());
        }

        grid.setItems(persons);
    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MainUI that = (MainUI) o;

        if (grid != null ? !grid.equals(that.grid) : that.grid != null) return false;
        return persons != null ? persons.equals(that.persons) : that.persons == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (grid != null ? grid.hashCode() : 0);
        result = 31 * result + (persons != null ? persons.hashCode() : 0);
        return result;
    }
}
