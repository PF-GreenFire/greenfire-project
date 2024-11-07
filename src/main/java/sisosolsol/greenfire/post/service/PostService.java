package sisosolsol.greenfire.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.common.exception.BadRequestException;
import sisosolsol.greenfire.common.exception.NotFoundException;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;
import sisosolsol.greenfire.post.model.dao.PostMapper;
import sisosolsol.greenfire.post.model.dto.PostCreateDTO;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;
import sisosolsol.greenfire.post.model.type.PostType;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public List<SimplePostDTO> getChallengePostList(Integer challengeCode) {
        return postMapper.getChallengePostList(challengeCode);
    }

    public int registChallengePost(PostCreateDTO post) {
        // TODO: throw error if postType is not challenge
        try {
            postMapper.registChallengePost(post);
        } catch (DataAccessException e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new BadRequestException(ExceptionCode.InvalidForeignKeyException);
            } else {
                System.out.println("에러 발생" + e.getMessage());
            }
        }
        return post.getPostCode();
    }
}
