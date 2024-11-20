package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.challenge.ChallengeStatus;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChallengeCreateDTO {

    private Integer challengeCode;
    private OffsetDateTime createdAt;
    private String challengeTitle;
    private String challengeContent;
    private Integer recruitmentNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID hostUser;
    private Integer xp;
    private String thumbnailUrl;
    private Integer challengeCategoryCode;
    private ChallengeStatus challengeStatus;

}
