package cs.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.applayout.AppLayout;
import cs.views.EventComponent;

public class PeopleChangeEvent extends ComponentEvent<EventComponent> {

    public PeopleChangeEvent(EventComponent source, boolean fromClient) {
        super(source, fromClient);
    }
}
