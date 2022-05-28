package cs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.models.Diagnosis;
import cs.models.Ward;
import cs.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@DependsOn("mainView")
@SpringComponent
@Scope("prototype")
public class DiagnosisGrid extends Grid<Diagnosis> {
    private MainView mainView;
    private DiagnosisService diagnosisService;

    public DiagnosisGrid(@Autowired MainView mainView,
                         @Autowired DiagnosisService diagnosisService) {
        super(Diagnosis.class);

        this.mainView = mainView;
        this.diagnosisService = diagnosisService;

        addClassNames("contact-grid");
        setSizeFull();
        setColumns("name");

        addComponentColumn(diagnosis -> {
            Button button = new Button("Delete");
            button.addClickListener((e) -> {
                if (diagnosis.getPeopleList().size() == 0) {
                    diagnosisService.delete(diagnosis.getId());
                    mainView.fireDiagnosisChangeEvent();
                } else {
                    button.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    button.setText("Not empty");
                }
            });
            return button;
        }).setHeader("");

        getColumns().forEach(col -> col.setAutoWidth(true));

        addAttachListener((e) -> {
            updateGrid();
        });

        mainView.addDiagnosisChangeEventListener((e) -> {
            updateGrid();
        });
    }

    private void updateGrid() {
        setItems((List<Diagnosis>) diagnosisService.getAllDiagnoses());
    }

}
