package cs.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.exceptions.EmptyFieldException;
import cs.models.Diagnosis;
import cs.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

@DependsOn("mainView")
@SpringComponent
public class DiagnosisForm extends FormLayout {
    private MainView mainView;
    private TextField diagnosisName;
    private Button save;

    private DiagnosisService diagnosisService;

    public DiagnosisForm(@Autowired MainView mainView,
                         @Autowired DiagnosisService diagnosisService) {
        this.mainView = mainView;
        this.diagnosisService = diagnosisService;

        addClassName("contact-form");

        createChilds();

        add(diagnosisName,
                createButtonsLayout());
    }

    public void createChilds() {
        save = new Button("Save");
        save.addClickListener((e) -> {
            try {
                Diagnosis diagnosis = getDiagnosis();
                diagnosisService.save(diagnosis);
                mainView.fireDiagnosisChangeEvent();
            } catch (EmptyFieldException err) {
                System.out.println("Caught exception");
                return;
            }
        });

        diagnosisName = new TextField("Diagnosis");
        diagnosisName.setRequired(true);
    }

    private Diagnosis getDiagnosis() {
        String diagnosisNameValue = diagnosisName.getValue();

        if (diagnosisName.isEmpty() && diagnosisName.isRequired()) {
            diagnosisName.setInvalid(true);
        }

        if (diagnosisName.isInvalid()) {
            throw new EmptyFieldException("Invalid value");
        }

        diagnosisName.clear();
        diagnosisName.setInvalid(false);

        return new Diagnosis(diagnosisNameValue);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(save);
    }
}
