package pojoClassesForAPIs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@ToString
@Jacksonized
@Builder
public class AddCandidateRequestPojo {

    //variables
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String keywords;
    private String comment;
    private String dateOfApplication;
    private boolean consentToKeepData;
    private int vacancyId;
}
