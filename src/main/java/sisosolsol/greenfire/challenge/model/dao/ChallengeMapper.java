package sisosolsol.greenfire.challenge.model.dao;

import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;

import java.util.List;

public interface ChallengeMapper {

    List<ChallengeCreateDTO> getChallenges();

    void registChallenge(ChallengeCreateDTO challengeCreate);

}
