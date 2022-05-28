package cs.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.applayout.AppLayout;

public class WardChangeEvent extends ComponentEvent<AppLayout> {
    public WardChangeEvent(AppLayout source, boolean fromClient) {
        super(source, fromClient);
    }
}
