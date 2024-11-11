package sisosolsol.greenfire.challenge.model.dto;

import sisosolsol.greenfire.common.enums.challenge.ChallengeStatus;

import java.time.LocalDateTime;

public class ChallengeDTO {
    Integer challengeCode;
    LocalDateTime createdAt;
    String challengeTitle;
    String challengeContent;
    Integer hostMember;
    Integer recruitmentNum;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer xp;
    String thumbnailUrl;
    Integer challengeCategoryCode;
    // enum으로 쓰는지 확인 필요
    ChallengeStatus challengeStatus;
}
