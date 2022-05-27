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
public class PeopleView extends VerticalLayout {
    private MainView mainView;
    private PeopleSideLayout sideLayout;
    private PeopleGrid peopleGrid;

    private HorizontalLayout content;

    PeopleView(@Autowired PeopleSideLayout sideLayout,
               @Autowired MainView mainView,
               @Autowired PeopleGrid peopleGrid) {
        this.sideLayout = sideLayout;
        this.mainView = mainView;
        this.peopleGrid = peopleGrid;

        addClassName("list-view");
        setSizeFull();
        createChilds();

        add(content);
    }

    private void createChilds() {
        createContent();
    }

    private void createContent() {
        content = new HorizontalLayout(peopleGrid, sideLayout);
        content.setFlexGrow(2, peopleGrid);
        content.setFlexGrow(1, sideLayout);
        content.addClassNames("content");
        content.setSizeFull();
    }
}
