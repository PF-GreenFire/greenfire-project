package sisosolsol.greenfire.challenge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.challenge.model.dao.ChallengeMapper;
import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;

import java.util.List;

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

    public List<ChallengeCreateDTO> getChallenges() {
        return challengeMapper.getChallenges();
    }
}
