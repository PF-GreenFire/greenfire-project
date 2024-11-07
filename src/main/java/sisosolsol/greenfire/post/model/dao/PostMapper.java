package sisosolsol.greenfire.post.model.dao;

import org.apache.ibatis.annotations.Mapper;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;

import java.util.List;

@Mapper
public interface PostMapper {
    List<SimplePostDTO> getChallengePostList(Integer challengeCode);
}
