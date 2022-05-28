package cs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import cs.models.Ward;
import cs.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import java.util.List;

@DependsOn("mainView")
@SpringComponent
@Scope("prototype")
public class WardGrid extends Grid<Ward> {
    private MainView mainView;
    private WardService wardService;

    public WardGrid(@Autowired MainView mainView, @Autowired WardService wardService) {
        super(Ward.class);

        this.mainView = mainView;
        this.wardService = wardService;

        addClassNames("contact-grid");
        setSizeFull();
        setColumns("name", "maxCount");

        addComponentColumn(ward -> {
            Button button = new Button("Delete");
            button.addClickListener((e) -> {
                if (ward.getPeoples().size() == 0) {
                    wardService.delete(ward.getId());
                    mainView.fireWardChangeEvent();
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

        mainView.addWardChangeEventListener((e) -> {
            updateGrid();
        });
    }

    private void updateGrid() {
        setItems((List<Ward>) wardService.getAll());
    }
}
