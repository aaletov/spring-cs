package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Diagnosis {

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "diagnosis")
    private List<People> peopleList;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    public Diagnosis() {}

    public Diagnosis(String name) {
        this.name = name;
    }

}
