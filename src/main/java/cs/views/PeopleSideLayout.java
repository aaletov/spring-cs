package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.LinkedHashMap;

@DependsOn("mainView")
@SpringComponent
@Scope("prototype")
public class PeopleSideLayout extends VerticalLayout {
    private PeopleForm peopleForm;
    private MoveForm moveForm;

    private Tabs tabs;
    private Component currentComponent;
    private LinkedHashMap<Tab, Component> tabComponentMap;

    PeopleSideLayout(@Autowired PeopleForm peopleForm,
                     @Autowired MoveForm moveForm) {
        this.peopleForm = peopleForm;
        this.moveForm = moveForm;

        setWidth("");
        peopleForm.setWidth("25em");
        moveForm.setWidth("25em");

        this.tabComponentMap = new LinkedHashMap<>() {{
            put(new Tab("Create"), peopleForm);
            put(new Tab("Move"), moveForm);
        }};

        Tabs tabs = new Tabs();
        this.tabs = tabs;
        tabComponentMap.keySet().stream().forEach((tab) -> tabs.add(tab));
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        add(tabs, peopleForm);
        currentComponent = peopleForm;

        tabs.addSelectedChangeListener((e) -> {
            updateTab(e);
        });
    }

    public void updateTab(Tabs.SelectedChangeEvent e) {
        remove(currentComponent);

        Component component = tabComponentMap.get(e.getSelectedTab());
        add(component);
        currentComponent = component;
    }
}
