package pl.harpi.samples.j2ee.demo.person.gui;

import com.vaadin.ui.*;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Created by arkad on 09.05.2017.
 */
public class WindowModalNewPerson extends Window {
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

            WebTarget targetPostPerson = ClientBuilder.newClient().target("http://localhost:8080/j2ee-demo-app/rest/v1/repository/persons");

            targetPostPerson.request(MediaType.APPLICATION_JSON).post(Entity.entity(person, MediaType.APPLICATION_JSON));

            this.close();
            this.parentUI.updateList();
        });

        btnCancel.addClickListener(e -> this.close());

        bottomToolbar.addComponents(btnSave, btnCancel);

        FormLayout formLayout = new FormLayout();

        formLayout.addComponents(txfFirstName, txfLastName);

        layout.addComponents(formLayout, bottomToolbar);

        this.setContent(layout);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WindowModalNewPerson that = (WindowModalNewPerson) o;

        if (txfFirstName != null ? !txfFirstName.equals(that.txfFirstName) : that.txfFirstName != null) return false;
        return txfLastName != null ? txfLastName.equals(that.txfLastName) : that.txfLastName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (txfFirstName != null ? txfFirstName.hashCode() : 0);
        result = 31 * result + (txfLastName != null ? txfLastName.hashCode() : 0);
        return result;
    }
}
