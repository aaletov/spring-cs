package cs.models;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class People {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Getter
    private String firstName;

    @NotNull
    @Getter
    private String lastName;

    @NotNull
    @Getter
    private String patherName;

    @JsonProperty("diagnosis_id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    @Getter
    private Diagnosis diagnosis;

    @JsonProperty("ward_id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "ward_id")
    @Getter
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
