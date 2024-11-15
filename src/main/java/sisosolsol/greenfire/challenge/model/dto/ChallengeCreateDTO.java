package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.challenge.ChallengeStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChallengeCreateDTO {

    private Integer challengeCode;
    private LocalDateTime createdAt;
    private String challengeTitle;
    private String challengeContent;
    private Integer recruitmentNum;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID hostUser;
    private Integer xp;
    private String thumbnailUrl;
    private Integer challengeCategoryCode;
    private ChallengeStatus challengeStatus;

}
