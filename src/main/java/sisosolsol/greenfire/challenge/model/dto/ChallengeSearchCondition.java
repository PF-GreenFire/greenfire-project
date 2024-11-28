package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChallengeSearchCondition {
    private Integer offset;
    private Integer size;
    private String searchKeyword;
    private Integer categoryCode;
}
