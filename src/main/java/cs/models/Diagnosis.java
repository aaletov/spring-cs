package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Diagnosis {

    @JsonManagedReference
    @OneToMany(mappedBy = "diagnosis")
    @Getter
    private List<People> peopleList;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

    @NotNull
    @Column(unique = true)
    @Getter
    private String name;

    public Diagnosis() {}

    public Diagnosis(String name) {
        this.name = name;
    }

}
