package pojoClassesForAPIs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@ToString
@Jacksonized
@Builder
public class DeleteCandidateRequestPojo {

    //variables
    private List<Integer> ids;
}
