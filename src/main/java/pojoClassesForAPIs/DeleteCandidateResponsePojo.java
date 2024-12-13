package pojoClassesForAPIs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DeleteCandidateResponsePojo {

    private List<String> data;
    private List<Object> meta;
    private List<Object> rels;
}
