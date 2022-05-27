package cs.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.exceptions.EmptyFieldException;
import cs.models.Ward;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class WardForm extends FormLayout {
    private MainView mainView;
    private TextField wardName;
    private TextField maxCapacity;
    private Button save;

    private WardService wardService;

    public WardForm(@Autowired MainView mainView, @Autowired WardService wardService) {
        this.mainView = mainView;
        this.wardService = wardService;

        addClassName("contact-form");

        createChilds();

        add(wardName,
                maxCapacity,
                createButtonsLayout());
    }

    public void createChilds() {
        save = new Button("Save");
        save.addClickListener((e) -> {
           try {
               Ward ward = getWard();
               wardService.save(ward);
               mainView.fireWardChangeEvent();
           } catch (EmptyFieldException err) {
               System.out.println("Caught exception");
               return;
           }
        });

        wardName = new TextField("Ward name");
        wardName.setRequired(true);

        maxCapacity = new TextField("Max capacity");
        maxCapacity.setRequired(true);
    }

    private Ward getWard() {
        String wardNameValue = wardName.getValue();

        if (wardName.isEmpty() && wardName.isRequired()) {
            wardName.setInvalid(true);
        }

        Integer maxCapacityValue = 0;

        try {
            maxCapacityValue = Integer.parseInt(maxCapacity.getValue());
        } catch (NumberFormatException e) {
            maxCapacity.setInvalid(true);
        }

        if (maxCapacity.isEmpty() && maxCapacity.isRequired()) {
            maxCapacity.setInvalid(true);
        }

        if (wardName.isInvalid() || maxCapacity.isInvalid()) {
            throw new EmptyFieldException("Invalid value");
        }

        wardName.clear();
        maxCapacity.clear();

        wardName.setInvalid(false);
        wardName.setInvalid(false);

        return new Ward(wardNameValue, maxCapacityValue);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(save);
    }
}
