package sisosolsol.greenfire.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.post.model.dao.PostMapper;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;

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
}
