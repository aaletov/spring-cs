package cs.apimodels;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PeopleApiModel {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String patherName;

    @NotNull
    private Integer diagnosisId;

    private Integer wardId;

    public PeopleApiModel() {}

    public PeopleApiModel(String firstName, String lastName, String patherName, Integer diagnosisId, Integer wardId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.diagnosisId = diagnosisId;
        this.wardId = wardId;
    }
}
