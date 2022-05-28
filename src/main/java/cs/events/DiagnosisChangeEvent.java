package cs.events;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.applayout.AppLayout;

public class DiagnosisChangeEvent extends ComponentEvent<AppLayout> {
    public DiagnosisChangeEvent(AppLayout source, boolean fromClient) {
        super(source, fromClient);
    }
}
