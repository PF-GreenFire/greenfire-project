package sisosolsol.greenfire.challenge.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.challenge.model.dto.ChallengeCreateDTO;

import java.util.List;

@Mapper
public interface ChallengeMapper {

    List<ChallengeCreateDTO> getChallenges();

    void registChallenge(ChallengeCreateDTO challengeCreate);

}
