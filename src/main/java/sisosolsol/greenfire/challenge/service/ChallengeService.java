package sisosolsol.greenfire.challenge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.challenge.model.dao.ChallengeMapper;
import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengePartDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeSearchCondition;
import sisosolsol.greenfire.challenge.model.dto.ChallengeSearchDTO;
import sisosolsol.greenfire.common.enums.challenge.ChallengeStatus;
import sisosolsol.greenfire.common.exception.CustomException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static sisosolsol.greenfire.common.exception.type.ExceptionCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeMapper challengeMapper;

    public Integer registChallenge(ChallengeCreateDTO challengeCreate) {
        challengeMapper.registChallenge(challengeCreate);
        return challengeCreate.getChallengeCode();
    }

    public ChallengeSearchDTO getChallenges(int page, int size, String searchKeyword, Integer categoryCode) {
        ChallengeSearchCondition condition = new ChallengeSearchCondition();
        condition.setOffset(page * size);
        condition.setSize(size);
        condition.setSearchKeyword(searchKeyword);
        condition.setCategoryCode(categoryCode);

        // 전체 개수 조회
        int totalCount = challengeMapper.countChallenges(condition);

        // 페이지 데이터 조회
        List<ChallengeDTO> challenges = challengeMapper.selectChallenges(condition);

        // 결과 데이터 설정
        ChallengeSearchDTO result = new ChallengeSearchDTO();
        result.setChallenges(challenges);
        result.setCurrentPage(page);
        result.setTotalCount(totalCount);
        result.setHasNext(totalCount > (page + 1) * size);

        return result;
    }

    @Transactional
    public void applyChallenge(Integer challengeCode) {

        // 임시
        String userCode = "1205bf73-b5ca-460a-9317-8a5b5d06e784";

        // 1. 챌린지 정보 조회
        ChallengeDTO challenge = challengeMapper.selectChallengeByCode(challengeCode);
        if (challenge == null) {
            throw new CustomException(CHALLENGE_NOT_FOUND);
        }

        // 2. 유효성 검사
        validateChallengeStatus(challenge, ChallengeAction.APPLY);

        // 3. 현재 참여자 수 확인
        int currentParticipants = challengeMapper.countCurrentParticipants(challengeCode);

        // 4. 모집 정원 확인
        if (currentParticipants >= challenge.getRecruitmentNum()) {
            throw new CustomException(CHALLENGE_FULL_CAPACITY);
        }

        // 5. 챌린지 참여 정보 저장
        ChallengePartDTO challengePart = new ChallengePartDTO();
        challengePart.setChallengeCode(challengeCode);
        challengePart.setUserCode(UUID.fromString(userCode));
        challengeMapper.insertChallengePart(challengePart);
    }

    @Transactional
    public void cancelChallengePart(Integer challengeCode) {
        // 임시 사용자 코드 (실제 구현시 인증된 사용자 정보 사용)
        String userCode = "1205bf73-b5ca-460a-9317-8a5b5d06e784";

        // 1. 챌린지 정보 조회
        ChallengeDTO challenge = challengeMapper.selectChallengeByCode(challengeCode);
        if (challenge == null) {
            throw new CustomException(CHALLENGE_INVALID_STATUS);
        }

        // 2. 참여 정보 확인
        ChallengePartDTO participation = challengeMapper.selectChallengePart(challengeCode, UUID.fromString(userCode));
        if (participation == null) {
            throw new CustomException(CHALLENGE_NOT_PARTICIPATED);
        }

        // 3. 챌린지 상태 검증
        validateChallengeStatus(challenge, ChallengeAction.CANCEL);
        // TODO : 이미 관련 게시글이나 기타 연관 관계가 생긴 경우 처리하는 로직도 추후 회의 후 구현

        // 4. 챌린지 취소 처리
        int result = challengeMapper.cancelChallengePart(challengeCode, UUID.fromString(userCode));
        if (result == 0) {
            throw new CustomException(CHALLENGE_CANCEL_FAILED);
        }
    }

    /**
     * 챌린지 상태 검증을 위한 enum
     */
    private enum ChallengeAction {
        APPLY,   // 챌린지 참여
        CANCEL   // 챌린지 취소
    }

    /**
     * 챌린지 상태 검증
     * @param challenge 챌린지 정보
     * @param action 수행하려는 작업
     */
    private void validateChallengeStatus(ChallengeDTO challenge, ChallengeAction action) {
        LocalDateTime now = LocalDateTime.now();

        switch (action) {
            case APPLY:
                // 모집 중이 아닌 경우
                if (challenge.getChallengeStatus() != ChallengeStatus.RECRUITING) {
                    throw new CustomException(CHALLENGE_INVALID_STATUS);
                }

                // 이미 시작된 챌린지인 경우
                if (now.isAfter(challenge.getStartDate().atStartOfDay())) {
                    throw new CustomException(CHALLENGE_ALREADY_STARTED);
                }

                // 이미 참여 중인지 확인 (사용자 코드는 실제 구현시 인증된 사용자 정보 사용)
                String userCode = "1205bf73-b5ca-460a-9317-8a5b5d06e784";
                ChallengePartDTO existingPart = challengeMapper.selectChallengePart(
                        challenge.getChallengeCode(),
                        UUID.fromString(userCode)
                );
                if (existingPart != null) {
                    throw new CustomException(CHALLENGE_ALREADY_PARTICIPATED);
                }
                break;

            case CANCEL:
                // 챌린지 상태별 취소 가능 여부 검증
                switch (challenge.getChallengeStatus()) {
                    case RECRUITING:
                        // 모집 중인 상태에서만 취소 가능
                        if (now.isAfter(challenge.getStartDate().atStartOfDay())) {
                            throw new CustomException(CHALLENGE_ALREADY_STARTED);
                        }
                        break;
                    case ONGOING:
                        throw new CustomException(CHALLENGE_ALREADY_STARTED);
                    case CLOSED:
                        throw new CustomException(CHALLENGE_ALREADY_COMPLETED);
                    case CANCELLED:
                        throw new CustomException(CHALLENGE_ALREADY_CANCELLED);
                    case PAUSED:
                        throw new CustomException(CHALLENGE_PAUSED);
                }
                break;
        }
    }
}
