package sisosolsol.greenfire.challenge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.challenge.model.dao.ChallengeMapper;
import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeSearchCondition;
import sisosolsol.greenfire.challenge.model.dto.ChallengeSearchDTO;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeMapper challengeMapper;

    public Integer registChallenge(ChallengeCreateDTO challengeCreate) {
        challengeCreate.setCreatedAt(LocalDateTime.now());
        System.out.println("Challenge Title: " + challengeCreate.getChallengeTitle());
        System.out.println("시간: " + challengeCreate.getCreatedAt());
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
}
