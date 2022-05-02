package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ward {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer maxCount;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "ward")
    private List<People> peoples;

    public Ward() {}

    public Ward(String name, Integer maxCount) {
        this.name = name;
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return name + " capacity " + maxCount;
    }

}
