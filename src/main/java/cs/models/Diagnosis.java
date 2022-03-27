package cs.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    public Diagnosis(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Diagnosis() {

    }
}
