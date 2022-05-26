package cs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.models.People;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@SpringComponent
@Scope("prototype")
public class PeopleView extends VerticalLayout {
    private EventComponent eventComponent;
    private PeopleSideLayout sideLayout;
    private PeopleService peopleService;
    private DiagnosisService diagnosisService;
    private WardService wardService;

    private HorizontalLayout content;
    private Grid<People> grid;

    PeopleView(@Autowired PeopleSideLayout sideLayout,
               @Autowired PeopleService peopleService,
               @Autowired DiagnosisService diagnosisService,
               @Autowired WardService wardService,
               @Autowired EventComponent eventComponent) {
        this.sideLayout = sideLayout;
        this.peopleService = peopleService;
        this.diagnosisService = diagnosisService;
        this.wardService = wardService;
        this.eventComponent = eventComponent;

        addClassName("list-view");
        setSizeFull();
        createChilds();

        add(content);
    }

    private void createChilds() {
        createGrid();
        createContent();
    }

    private void createContent() {
        content = new HorizontalLayout(grid, sideLayout);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, sideLayout);
        content.addClassNames("content");
        content.setSizeFull();
    }

    private void createGrid() {
        grid = new Grid<>(People.class);
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "patherName");

        grid.addColumn(people -> people.getWard().getName()).setHeader("Ward");
        grid.addColumn(people -> people.getDiagnosis().getName()).setHeader("Diagnosis");

        grid.addComponentColumn(people -> {
            Button button = new Button("Delete");
            button.addClickListener((e) -> {
                peopleService.delete(people);
                eventComponent.firePeopleChangeEvent();
            });
            return button;
        }).setHeader("");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        updateGrid();
    }

    private void updateGrid() {
        grid.setItems((List<People>) peopleService.getAll());
    }
}
