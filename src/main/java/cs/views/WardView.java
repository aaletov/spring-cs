package cs.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class WardView extends VerticalLayout {
    private MainView mainView;
    private WardSideLayout sideLayout;
    private WardGrid wardGrid;

    private HorizontalLayout content;

    public WardView(@Autowired MainView mainView,
             @Autowired WardSideLayout sideLayout,
             @Autowired WardGrid wardGrid) {
        this.mainView = mainView;
        this.sideLayout = sideLayout;
        this.wardGrid = wardGrid;

        addClassName("list-view");
        setSizeFull();
        createChilds();

        add(content);
    }

    private void createChilds() {
        createContent();
    }

    private void createContent() {
        content = new HorizontalLayout(wardGrid, sideLayout);
        content.setFlexGrow(2, wardGrid);
        content.setFlexGrow(1, sideLayout);
        content.addClassNames("content");
        content.setSizeFull();
    }
}
