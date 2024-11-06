package sisosolsol.greenfire.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;
import sisosolsol.greenfire.post.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/challenge/{challengeCode}")
    public ResponseEntity<List<SimplePostDTO>> getChallengePostList(@PathVariable Integer challengeCode) {
        List<SimplePostDTO> postList = postService.getChallengePostList(challengeCode);
        return ResponseEntity.ok(postList);
    }
}
