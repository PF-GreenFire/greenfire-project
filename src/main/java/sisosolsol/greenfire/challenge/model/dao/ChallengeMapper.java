package sisosolsol.greenfire.challenge.model.dao;

import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeDTO;
import sisosolsol.greenfire.challenge.model.dto.ChallengeSearchCondition;

import java.util.List;

public interface ChallengeMapper {

    void registChallenge(ChallengeCreateDTO challengeCreate);

    int countChallenges(ChallengeSearchCondition condition);

    List<ChallengeDTO> selectChallenges(ChallengeSearchCondition condition);
}
