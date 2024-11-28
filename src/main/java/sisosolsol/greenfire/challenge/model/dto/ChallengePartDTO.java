package sisosolsol.greenfire.challenge.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ChallengePartDTO {
    private Integer challengePartCode;
    private Integer challengeCode;
    private UUID userCode;
    private OffsetDateTime createdAt;
    private boolean isHost;
}
