package cs.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@DependsOn("mainView")
@SpringComponent
@Scope("prototype")
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
