package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ward {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer maxCount;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ward")
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
