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

    @NotNull
    private Integer wardId;

    public PeopleApiModel() {}
}
