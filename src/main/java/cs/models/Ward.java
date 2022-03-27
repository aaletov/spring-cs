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
    private String name;

    @NotNull
    private Integer maxCount;

    @OneToMany(mappedBy = "ward")
    private List<People> peopleList;

    public Ward() {}
}
