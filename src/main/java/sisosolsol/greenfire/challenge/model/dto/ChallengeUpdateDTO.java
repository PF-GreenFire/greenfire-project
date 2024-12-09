package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import sisosolsol.greenfire.common.enums.challenge.ChallengeStatus;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ChallengeUpdateDTO {
    private Integer challengeCode;
    private String challengeTitle;
    private String challengeContent;
    private Integer recruitmentNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer xp;
    private String thumbnailUrl;
    private Integer challengeCategoryCode;
    private ChallengeStatus challengeStatus;
}
