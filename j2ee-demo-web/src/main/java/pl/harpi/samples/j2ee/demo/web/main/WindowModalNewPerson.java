package pl.harpi.samples.j2ee.demo.web.main;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.ui.*;
import pl.harpi.samples.j2ee.demo.web.domain.Person;
import pl.harpi.samples.j2ee.demo.web.domain.PersonRequest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by arkad on 09.05.2017.
 */
public class WindowModalNewPerson extends Window {
    Logger log = LoggerFactory.getLogger(WindowModalNewPerson.class);

    private MainUI parentUI;
    private TextField txfFirstName = new TextField("First name:");
    private TextField txfLastName = new TextField("Last name:");

    public WindowModalNewPerson(MainUI parentUI) {
        this.setCaption("New person");
        this.setModal(true);
        this.parentUI = parentUI;

        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);

        HorizontalLayout bottomToolbar = new HorizontalLayout();

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");

        btnSave.addClickListener(e -> {
            PersonRequest person = new PersonRequest();
            person.setFirstName(txfFirstName.getValue());
            person.setLastName(txfLastName.getValue());

            WebTarget targetPostPerson = ClientBuilder.newClient().target("http://localhost:8080/j2ee-demo-service/rest/v1/repository/persons");

            Response response = targetPostPerson.request(MediaType.APPLICATION_JSON).post(Entity.entity(person, MediaType.APPLICATION_JSON));

            this.close();
            parentUI.updateList();
        });

        btnCancel.addClickListener(e -> {
            this.close();
        });

        bottomToolbar.addComponents(btnSave, btnCancel);

        FormLayout formLayout = new FormLayout();

        formLayout.addComponents(txfFirstName, txfLastName);

        layout.addComponents(formLayout, bottomToolbar);

        this.setContent(layout);
    }


}
