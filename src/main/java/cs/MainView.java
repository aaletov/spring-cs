package cs;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Text("Welcome to MainView."));
    }

}