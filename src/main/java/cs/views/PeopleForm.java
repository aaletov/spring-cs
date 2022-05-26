package cs.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.exceptions.EmptyFieldException;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@SpringComponent
@Scope("prototype")
public class PeopleForm extends FormLayout {
    private MainView mainView;
    private TextField firstName;
    private TextField lastName;
    private TextField patherName;
    private ComboBox<Ward> ward;
    private ComboBox<Diagnosis> diagnosis;
    private Button save;

    PeopleService peopleService;
    WardService wardService;
    DiagnosisService diagnosisService;

    public PeopleForm(@Autowired PeopleService peopleService,
                      @Autowired WardService wardService,
                      @Autowired DiagnosisService diagnosisService,
                      @Autowired MainView mainView) {
        this.peopleService = peopleService;
        this.wardService = wardService;
        this.diagnosisService = diagnosisService;
        this.mainView = mainView;

        save = new Button("Save");

        addClassName("contact-form");

        configureForm();
        configureButtons();

        add(firstName,
                lastName,
                patherName,
                diagnosis,
                ward,
                createButtonsLayout());

        mainView.addPeopleChangeEventListener((e) -> {
            updateComboBoxes();
        });
    }

    private void configureForm() {
        this.firstName = new TextField("First name");
        firstName.setRequired(true);

        this.lastName = new TextField("Last name");
        lastName.setRequired(true);

        this.patherName = new TextField("Pather name");
        patherName.setRequired(true);

        this.diagnosis = new ComboBox<>("Diagnosis");
        diagnosis.setRequired(true);

        this.ward = new ComboBox<>("Ward");
        ward.setRequired(true);

        updateComboBoxes();

        diagnosis.setItemLabelGenerator(Diagnosis::getName);
        ward.setItemLabelGenerator(Ward::getName);
    }

    private void updateComboBoxes() {
        List<Diagnosis> diagnoses = (List<Diagnosis>) diagnosisService.getAllDiagnoses();
        List<Ward> wards = (List<Ward>) wardService.getAll();

        diagnosis.setItems(diagnoses);
        ward.setItems(wards);
    }

    private String getTextFieldValue(TextField field) {
        if (field.isEmpty() && field.isRequired()) {
            field.setInvalid(true);
            return null;
        }

        field.setInvalid(false);
        return field.getValue();
    }

    private People getPeople() {
        String firstNameValue = getTextFieldValue(firstName);
        String lastNameValue = getTextFieldValue(lastName);
        String patherNameValue = getTextFieldValue(patherName);

        if (diagnosis.isEmpty() && diagnosis.isRequired()) {
            diagnosis.setInvalid(true);
        }
        Diagnosis diagnosisValue = diagnosis.getValue();

        if (ward.isEmpty() && ward.isRequired()) {
            ward.setInvalid(true);
        }
        Ward wardValue = ward.getValue();

        if ((firstNameValue == null) || (lastNameValue == null) || (patherNameValue == null) ||
                (diagnosisValue == null) || (wardValue == null)) {
            throw new EmptyFieldException("Cannot be null");
        }

        firstName.clear();
        lastName.clear();
        patherName.clear();
        ward.clear();
        diagnosis.clear();

        firstName.setInvalid(false);
        lastName.setInvalid(false);
        patherName.setInvalid(false);
        ward.setInvalid(false);
        diagnosis.setInvalid(false);

        return new People(firstNameValue, lastNameValue, patherNameValue, diagnosisValue, wardValue);
    }


    private void configureButtons() {
        save.addClickListener((e) -> {
            try {
                People people = getPeople();
                peopleService.save(people);

                mainView.firePeopleChangeEvent();
            } catch (EmptyFieldException err) {
                System.out.println("Caught exception");
                return;
            }
        });
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(save);

    }
}