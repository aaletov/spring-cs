package cs.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diagnosis {

    @OneToMany(mappedBy = "diagnosis")
    private List<People> peopleList;

    @Id
    private Integer id;

    @NotNull
    private String name;

    public Diagnosis() {}
}
