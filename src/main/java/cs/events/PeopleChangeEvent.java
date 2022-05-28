package cs.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.applayout.AppLayout;

public class PeopleChangeEvent extends ComponentEvent<AppLayout> {

    public PeopleChangeEvent(AppLayout source, boolean fromClient) {
        super(source, fromClient);
    }
}
