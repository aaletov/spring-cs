package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;

@SpringComponent
public class WardSideLayout extends VerticalLayout {
    private MainView mainView;
    private WardService wardService;
    private WardForm wardForm;

    private Tabs tabs;
    private Component currentComponent;
    private LinkedHashMap<Tab, Component> tabComponentMap;

    WardSideLayout(@Autowired MainView mainView, @Autowired WardService wardService, @Autowired WardForm wardForm) {
        this.mainView = mainView;
        this.wardService = wardService;

        setWidth("");
        wardForm.setWidth("25em");

        this.tabComponentMap = new LinkedHashMap<>() {{
            put(new Tab("Create"), wardForm);
        }};

        Tabs tabs = new Tabs();
        this.tabs = tabs;
        tabComponentMap.keySet().stream().forEach((tab) -> tabs.add(tab));
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        add(tabs, wardForm);
        currentComponent = wardForm;

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
