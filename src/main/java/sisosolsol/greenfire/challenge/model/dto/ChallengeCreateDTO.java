package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sisosolsol.greenfire.common.ChallengeStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChallengeCreateDTO {

    private Integer challengeCode;
    private LocalDateTime createdAt;
    private String challengeName;
    private String challengeContent;
    private Integer recruitmentNum;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer hostMember;
    private Integer xp;
    private String thumbnailUrl;
    private Integer challengeCategoryCode;
    private ChallengeStatus challengeStatus;

}
