package cs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.exceptions.EmptyFieldException;
import cs.exceptions.NoSuchEntryException;
import cs.models.People;
import cs.models.Ward;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@DependsOn("mainView")
@SpringComponent
@Scope("prototype")
public class MoveForm extends FormLayout {
    private MainView mainView;
    private PeopleService peopleService;
    private WardService wardService;

    private ComboBox<People> peopleBox;
    private ComboBox<Ward> wardTarget;
    private Button moveButton;
    private Span isFull;

    MoveForm(@Autowired PeopleService peopleService,
             @Autowired WardService wardService,
             @Autowired MainView mainView) {
        this.peopleService = peopleService;
        this.wardService = wardService;
        this.mainView = mainView;

        configureFields();
        configureButtons();

        add(
                peopleBox,
                wardTarget,
                moveButton,
                isFull
        );

        mainView.addPeopleChangeEventListener((e) -> updateComboBoxes());
    }

    private void configureFields() {
        this.peopleBox = new ComboBox<>("Person");
        peopleBox.setRequired(true);

        this.wardTarget = new ComboBox<>("Target ward");
        wardTarget.setRequired(true);

        updateComboBoxes();
    }

    public void updateComboBoxes() {
        peopleBox.clear();
        wardTarget.clear();

        List<People> people = (List<People>) peopleService.getAll();
        peopleBox.setItems(people);

        List<Ward> wards = (List<Ward>) wardService.getAll();
        wardTarget.setItems(wards);
    }


    private void configureButtons() {
        moveButton = new Button("Confirm");
        isFull = new Span("Is full");
        isFull.getElement().getThemeList().add("badge error normal");
        isFull.setVisible(false);

        moveButton.addClickListener((e) -> {
            try {
                movePerson();
                mainView.firePeopleChangeEvent();
                isFull.setVisible(false);
            } catch (EmptyFieldException err) {
                System.out.println("Caught exception");
                return;
            } catch (IllegalArgumentException err) {
                isFull.setVisible(true);
            }
        });
    }

    private void movePerson() {
        if (peopleBox.isEmpty() && peopleBox.isRequired()) {
            peopleBox.setInvalid(true);
        }
        People person = peopleBox.getValue();

        if (wardTarget.isEmpty() && wardTarget.isRequired()) {
            wardTarget.setInvalid(true);
        }
        Ward wardTargetValue = wardTarget.getValue();

        if ((person == null) || (wardTargetValue == null)) {
            throw new EmptyFieldException("Cannot be null");
        }

        if (wardTargetValue.getPeoples().size() == wardTargetValue.getMaxCount()) {
            throw new IllegalArgumentException("Ward is full");
        }

        peopleBox.clear();
        wardTarget.clear();

        try {
            peopleService.patchPeopleWard(person.getId(), wardTargetValue.getId());
        } catch (NoSuchEntryException ex) {
            ex.printStackTrace();
        }
    }
}
