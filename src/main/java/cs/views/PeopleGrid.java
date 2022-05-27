package cs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.models.People;
import cs.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@SpringComponent
@Scope("prototype")
public class PeopleGrid extends Grid<People> {
    private MainView mainView;
    private PeopleService peopleService;

    public PeopleGrid(@Autowired PeopleService peopleService,
                      @Autowired MainView mainView) {
        super(People.class);

        this.peopleService = peopleService;
        this.mainView = mainView;

        addClassNames("contact-grid");
        setSizeFull();
        setColumns("firstName", "lastName", "patherName");

        addColumn(people -> people.getWard().getName()).setHeader("Ward");
        addColumn(people -> people.getDiagnosis().getName()).setHeader("Diagnosis");

        addComponentColumn(people -> {
            Button button = new Button("Delete");
            button.addClickListener((e) -> {
                peopleService.delete(people);
                mainView.firePeopleChangeEvent();
            });
            return button;
        }).setHeader("");

        getColumns().forEach(col -> col.setAutoWidth(true));

        addAttachListener((e) -> {
            updateGrid();
        });

        mainView.addPeopleChangeEventListener((e) -> {
            updateGrid();
        });
    }

    private void updateGrid() {
        setItems((List<People>) peopleService.getAll());
    }
}
