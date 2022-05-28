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
public class DiagnosisView extends VerticalLayout {
    private MainView mainView;
    private DiagnosisSideLayout sideLayout;
    private DiagnosisGrid diagnosisGrid;

    private HorizontalLayout content;

    public DiagnosisView(@Autowired MainView mainView,
                         @Autowired DiagnosisSideLayout sideLayout,
                         @Autowired DiagnosisGrid diagnosisGrid) {
        this.mainView = mainView;
        this.sideLayout = sideLayout;
        this.diagnosisGrid = diagnosisGrid;

        addClassName("list-view");
        setSizeFull();
        createChilds();

        add(content);
    }

    private void createChilds() {
        createContent();
    }

    private void createContent() {
        content = new HorizontalLayout(diagnosisGrid, sideLayout);
        content.setFlexGrow(2, diagnosisGrid);
        content.setFlexGrow(1, sideLayout);
        content.addClassNames("content");
        content.setSizeFull();
    }
}
