package cs.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Diagnosis {

    @JsonManagedReference
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
