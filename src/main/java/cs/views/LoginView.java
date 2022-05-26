package cs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout {
    LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        HorizontalLayout header = new HorizontalLayout();
        add(header);

        header.setSizeUndefined();
        header.setWidth(98, Unit.PERCENTAGE);
        header.setAlignItems(Alignment.END);
        header.setJustifyContentMode(JustifyContentMode.END);

        Button button = new Button("Register");
        header.add(button);

        button.addClickListener((e) -> {
            UI.getCurrent().navigate("/register");
        });

        VerticalLayout inner = new VerticalLayout();
        add(inner);

        inner.setSizeUndefined();

        LoginForm login = new LoginForm();
        inner.add(login);

        login.setForgotPasswordButtonVisible(false);
        login.setAction("/login");
    }
}
