package sisosolsol.greenfire.challenge.util;

import lombok.RequiredArgsConstructor;
import sisosolsol.greenfire.challenge.model.dto.ChallengeDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeUpdateDTO;
import sisosolsol.greenfire.common.exception.CustomException;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static sisosolsol.greenfire.common.exception.type.ExceptionCode.CHALLENGE_ALREADY_STARTED;
import static sisosolsol.greenfire.common.exception.type.ExceptionCode.CHALLENGE_INVALID_STATUS;

@RequiredArgsConstructor
public class ChallengeStatusValidator {

    private final ChallengeDTO challenge;
    private final ChallengeUpdateDTO updateDTO;

    public void validateStatus() {
        switch (challenge.getChallengeStatus()) {
            case CANCELLED:
                throw new CustomException(ExceptionCode.CHALLENGE_ALREADY_CANCELLED);
            case CLOSED:
                throw new CustomException(ExceptionCode.CHALLENGE_ALREADY_COMPLETED);
            case PAUSED:
                throw new CustomException(ExceptionCode.CHALLENGE_PAUSED);
            case ONGOING:
                validateOngoingChallengeUpdate();
                break;
            case RECRUITING:
                // 모집 중인 상태는 제약 없음
                break;
        }
    }

    private void validateOngoingChallengeUpdate() {
        List<String> violationMessages = new ArrayList<>();

        checkField(challenge.getRecruitmentNum(), updateDTO.getRecruitmentNum(), "모집 인원", violationMessages);
        checkField(challenge.getStartDate(), updateDTO.getStartDate(), "시작일", violationMessages);
        checkField(challenge.getXp(), updateDTO.getXp(), "XP", violationMessages);
        checkField(challenge.getChallengeCategoryCode(), updateDTO.getChallengeCategoryCode(), "카테고리", violationMessages);

        if (!violationMessages.isEmpty()) {
            String errorDetail = String.format(
                    "진행 중인 챌린지의 다음 항목은 수정할 수 없습니다: %s",
                    String.join(", ", violationMessages)
            );
            throw new CustomException(CHALLENGE_ALREADY_STARTED);
        }

        validateEndDate();
    }

    private void validateEndDate() {
        if (updateDTO.getEndDate() != null &&
                updateDTO.getEndDate().isBefore(LocalDate.now())) {
            throw new CustomException(CHALLENGE_INVALID_STATUS);
        }
    }

    private <T> void checkField(T currentValue, T newValue, String fieldName, List<String> violations) {
        if (newValue != null && !Objects.equals(currentValue, newValue)) {
            violations.add(fieldName);
        }
    }
}
