package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
@SpringComponent
@Route("")
public class MainView extends AppLayout {
    private PeopleService peopleService;
    private DiagnosisService diagnosisService;
    private WardService wardService;
    private PeopleView peopleView;
    private EventComponent eventComponent;
    private ApplicationContext applicationContext;

    private Tabs tabs;
    private H1 title;

    private LinkedHashMap<Tab, Component> tabComponentMap;

    @Bean
    public EventComponent getEventComponent() {
        return eventComponent;
    }

    public MainView(@Autowired PeopleService peopleService,
                    @Autowired DiagnosisService diagnosisService,
                    @Autowired WardService wardService,
                    //@Autowired PeopleView peopleView,
                    //@Autowired EventComponent eventComponent,
                    @Autowired ApplicationContext applicationContext) {
        this.peopleService = peopleService;
        this.diagnosisService = diagnosisService;
        this.wardService = wardService;
        //this.peopleView = peopleView;
        this.eventComponent = new EventComponent();
        this.applicationContext = applicationContext;

        addAttachListener((e) -> {
            peopleView = applicationContext.getBean(PeopleView.class);

            createChilds();
            addToNavbar(title, tabs);

            tabs.addSelectedChangeListener((event) -> {
                updateTab(event);
            });
        });

        this.addToNavbar(eventComponent);
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
            put(new Tab("Wards"), new Text("Sheesh"));
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
}