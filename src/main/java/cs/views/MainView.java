package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.events.DiagnosisChangeEvent;
import cs.events.PeopleChangeEvent;
import cs.events.WardChangeEvent;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import jdk.jshell.Diag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@SpringComponent
@Route("")
public class MainView extends AppLayout {
    private PeopleService peopleService;
    private DiagnosisService diagnosisService;
    private WardService wardService;
    private PeopleView peopleView;
    private WardView wardView;
    private ApplicationContext applicationContext;

    private Tabs tabs;
    private H1 title;

    private LinkedHashMap<Tab, Component> tabComponentMap;

    public MainView(@Autowired PeopleService peopleService,
                    @Autowired DiagnosisService diagnosisService,
                    @Autowired WardService wardService,
                    @Autowired ApplicationContext applicationContext) {
        this.peopleService = peopleService;
        this.diagnosisService = diagnosisService;
        this.wardService = wardService;
        this.applicationContext = applicationContext;

        addAttachListener((e) -> {
            peopleView = applicationContext.getBean(PeopleView.class);
            wardView = applicationContext.getBean(WardView.class);

            createChilds();
            addToNavbar(title, tabs);

            tabs.addSelectedChangeListener((event) -> {
                updateTab(event);
            });
        });
    }

    private void createChilds() {
        title = new H1("HospitalApp");
        title.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("left", "var(--lumo-space-l)")
                .set("margin-top", "0")
                .set("margin-bottom", "0")
                .set("margin-left", "50px")
                .set("margin-right", "30px");

        this.tabComponentMap = new LinkedHashMap<>() {{
            put(new Tab("Dashboard"), new Text("Sheesh"));
            put(new Tab("People"), peopleView);
            put(new Tab("Diagnoses"), new Text("Sheesh"));
            put(new Tab("Wards"), wardView);
        }};

        Tabs tabs = new Tabs();
        this.tabs = tabs;
        tabComponentMap.keySet().stream().forEach((tab) -> tabs.add(tab));
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
    }

    public void updateTab(Tabs.SelectedChangeEvent e) {
        Component component = tabComponentMap.get(e.getSelectedTab());
        setContent(component);
    }

    public Registration addPeopleChangeEventListener(ComponentEventListener<PeopleChangeEvent> listener) {
        return addListener(PeopleChangeEvent.class, listener);
    }

    public void firePeopleChangeEvent() {
        fireEvent(new PeopleChangeEvent(this, false));
    }

    public Registration addWardChangeEventListener(ComponentEventListener<WardChangeEvent> listener) {
        return addListener(WardChangeEvent.class, listener);
    }

    public void fireWardChangeEvent() {
        fireEvent(new WardChangeEvent(this, false));
    }

    public Registration addDiagnosisChangeEventListener(ComponentEventListener<DiagnosisChangeEvent> listener) {
        return addListener(DiagnosisChangeEvent.class, listener);
    }

    public void fireDiagnosisChangeEvent() {
        fireEvent(new DiagnosisChangeEvent(this, false));
    }
}