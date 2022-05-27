package cs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import cs.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

@DependsOn("mainView")
@Route("register")
public class RegisterView extends VerticalLayout {
    RegisterView(@Autowired RegisterService service) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        VerticalLayout inner = new VerticalLayout();
        add(inner);

        inner.setSizeUndefined();

        Label label = new Label("Register");
        label.getStyle()
                .set("font-size", "var(--lumo-font-size-xx1)");
        inner.add(label);

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");
        Button button = new Button("Confirm");

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        formLayout.add(
                username,
                password,
                confirmPassword,
                button
        );

        inner.add(
                formLayout
        );


        button.addClickListener((e) -> {
            UI.getCurrent().navigate("/login");
            service.processRegistration(username.getValue(), password.getValue());
        });
    }
}
