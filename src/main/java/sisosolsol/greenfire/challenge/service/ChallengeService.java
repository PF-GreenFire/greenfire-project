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

        // 2. 유효성 검사
        validateChallengeParticipation(challenge);

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
//        challengePart.setIsHost(false); isHost 보류

        challengeMapper.insertChallengePart(challengePart);
    }

    private void validateChallengeParticipation(ChallengeDTO challenge) {
        if (challenge == null) {
            throw new CustomException(CHALLENGE_NOT_FOUND);
        }

        if (challenge.getChallengeStatus() != ChallengeStatus.RECRUITING) {
            throw new CustomException(CHALLENGE_INVALID_STATUS);
        }

        if (LocalDateTime.now().isAfter(challenge.getStartDate().atStartOfDay())) {
            throw new CustomException(CHALLENGE_ALREADY_STARTED);
        }
    }
}
