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
public class AddEmployeeRequestPojo {

    //variables
    private String firstName;
    private String middleName;
    private String lastName;
    private String empPicture;
    private String employeeId;
}
