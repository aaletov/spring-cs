package cs.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer maxCount;

    @OneToMany(mappedBy = "ward")
    private List<People> peopleList;

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
