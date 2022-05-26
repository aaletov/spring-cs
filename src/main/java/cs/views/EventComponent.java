package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.ShadowRoot;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.component.Tag;
import cs.events.PeopleChangeEvent;
import org.springframework.context.annotation.Scope;

@Tag(value = "event-component")
public class EventComponent extends Component {
    EventComponent() {}

    public Registration addPeopleChangeEventListener(ComponentEventListener<PeopleChangeEvent> listener) {
        return addListener(PeopleChangeEvent.class, listener);
    }

    public void firePeopleChangeEvent() {
        fireEvent(new PeopleChangeEvent(this, false));
    }
}
