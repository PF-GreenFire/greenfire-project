package sisosolsol.greenfire.post.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import sisosolsol.greenfire.post.model.dto.PostCreateDTO;
import sisosolsol.greenfire.post.model.dto.PostDTO;
import sisosolsol.greenfire.post.model.dto.PostUpdateDTO;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;

import java.util.List;

@Mapper
public interface PostMapper {
    List<SimplePostDTO> getChallengePostList(Integer challengeCode);

    PostDTO getPost(Integer postCode);

    void registChallengePost(PostCreateDTO post) throws DataAccessException;

    void updatePost(@Param("postCode") Integer postCode, @Param("post") PostUpdateDTO post);

    void deletePost(Integer postCode);
}
