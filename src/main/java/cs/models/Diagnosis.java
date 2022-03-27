package cs.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Diagnosis {

    @OneToMany(mappedBy = "diagnosis")
    private List<People> peopleList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    public Diagnosis() {}

    public Diagnosis(String name) {
        this.name = name;
    }

}
