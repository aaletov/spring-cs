package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class People {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String patherName;

    @JsonProperty("diagnosis_id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @JsonProperty("ward_id")
    @JsonIdentityReference(alwaysAsId = true)
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
