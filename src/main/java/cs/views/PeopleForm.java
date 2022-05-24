package cs.views;

import ch.qos.logback.core.status.Status;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PeopleForm extends FormLayout {
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField patherName = new TextField("Pather name");

    ComboBox<Diagnosis> diagnosis = new ComboBox<>("Diagnosis");
    ComboBox<Ward> ward = new ComboBox<>("Ward");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    PeopleService peopleService;
    WardService wardService;
    DiagnosisService diagnosisService;

    public PeopleForm(List<Diagnosis> diagnoses, List<Ward> wards, PeopleService peopleService, WardService wardService, DiagnosisService diagnosisService) {
        this.peopleService = peopleService;
        this.wardService = wardService;
        this.diagnosisService = diagnosisService;

        addClassName("contact-form");

        diagnosis.setItems(diagnoses);
        ward.setItems(wards);

        diagnosis.setItemLabelGenerator(Diagnosis::getName);
        ward.setItemLabelGenerator(Ward::getName);

        configureButtons();

        add(firstName,
                lastName,
                patherName,
                diagnosis,
                ward,
                createButtonsLayout());
    }

    private void configureButtons() {
        save.addClickListener((e) -> {
           String firstNameValue = firstName.getValue();
           String lastNameValue = lastName.getValue();
           String patherNameValue = patherName.getValue();

           Diagnosis diagnosisValue = diagnosis.getValue();
           Ward wardValue = ward.getValue();

           peopleService.save(new People(firstNameValue, lastNameValue, patherNameValue, diagnosisValue, wardValue));

           firstName.clear();
           lastName.clear();
           patherName.clear();
        });

        delete.addClickListener((e) -> {
            String firstNameValue = firstName.getValue();
            String lastNameValue = lastName.getValue();
            String patherNameValue = patherName.getValue();

            Diagnosis diagnosisValue = diagnosis.getValue();
            Ward wardValue = ward.getValue();

            peopleService.delete(new People(firstNameValue, lastNameValue, patherNameValue, diagnosisValue, wardValue));

            firstName.clear();
            lastName.clear();
            patherName.clear();
        });

        close.addClickListener((e) -> {
            firstName.clear();
            lastName.clear();
            patherName.clear();
        });
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);

        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);


    }
}