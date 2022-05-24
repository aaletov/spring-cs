package cs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import cs.security.MyUser;
import cs.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("register")
public class RegisterView extends VerticalLayout {
    RegisterView(@Autowired RegisterService service) {
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm password");
        Button button = new Button("Confirm");

        FormLayout formLayout = new FormLayout();
        add(
            username,
            password,
            confirmPassword,
            button
        );

        button.addClickListener((e) -> {
            System.out.println("Clicked");
            UI.getCurrent().navigate("/");
            service.processRegistration(username.getValue(), password.getValue());
        });
    }
}
