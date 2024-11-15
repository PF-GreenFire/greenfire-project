package sisosolsol.greenfire.common;

import lombok.Getter;

@Getter
public enum ChallengeStatus {

    RECRUITING ("모집중"),
    // 참여자들을 모집하는 상태
    ONGOING ("진행중"),
    // 챌린지가 현재 진행되고 있는 상태
    CLOSED ("마감"),
    // 챌린지가 완료된 상태
    CANCELLED ("취소 및 삭제"),
    // 챌린지가 취소되거나 삭제된 상태
    PAUSED ("일시중지");
    // 일시적으로 중단된 상태

    private final String challengeStatus;
    ChallengeStatus(String challengeStatus) {
        this.challengeStatus = challengeStatus;
    }
}
