package pojoClassesForAPIs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AddCandidateResponsePojo {

    private Data data;
    private List<Object> meta;
    private List<Object> rels;

    @Getter
    @Setter
    @ToString
    public class Data {
        private int id;
        private String firstName;
        private String middleName;
        private String lastName;
        private String email;
    }
}
