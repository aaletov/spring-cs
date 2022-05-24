package cs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.services.DiagnosisService;
import cs.services.PeopleService;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("people")
public class PeopleView extends VerticalLayout {
    PeopleService peopleService;
    DiagnosisService diagnosisService;
    WardService wardService;

    PeopleForm form;
    Grid<People> grid = new Grid<>(People.class);

    PeopleView(@Autowired PeopleService peopleService, @Autowired DiagnosisService diagnosisService, @Autowired WardService wardService) {
        this.peopleService = peopleService;
        this.diagnosisService = diagnosisService;
        this.wardService = wardService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getContent());

        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new PeopleForm((List<Diagnosis>) diagnosisService.getAllDiagnoses(), (List<Ward>) wardService.getAll(),
                peopleService, wardService, diagnosisService);

        form.setWidth("25em");
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "patherName");

        grid.addColumn(people -> people.getWard().getName()).setHeader("Ward");
        grid.addColumn(people -> people.getDiagnosis().getName()).setHeader("Diagnosis");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        Button addPersonButton = new Button("Add person");

        HorizontalLayout toolbar = new HorizontalLayout(addPersonButton);

        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems((List<People>) peopleService.getAll());
    }
}
