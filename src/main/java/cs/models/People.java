package cs.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String patherName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    public People() {}

    public People(String firstName, String lastName, String patherName, Diagnosis diagnosis, Ward ward) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.diagnosis = diagnosis;
        this.ward = ward;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(firstName)
                .append(lastName)
                .append(patherName)
                .toString();
    }

}
