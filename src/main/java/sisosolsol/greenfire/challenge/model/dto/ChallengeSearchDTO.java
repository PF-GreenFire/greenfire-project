package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ChallengeSearchDTO {
    private List<ChallengeDTO> challenges;
    private boolean hasNext;
    private int totalCount;
    private int currentPage;
}
