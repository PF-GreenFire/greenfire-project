package sisosolsol.greenfire.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisosolsol.greenfire.post.model.dto.PostCreateDTO;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;
import sisosolsol.greenfire.post.service.PostService;

import java.net.URI;
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

    @PostMapping("/challenge")
    public ResponseEntity<Void> createChallengePost(@RequestBody PostCreateDTO post) {
        int postCode = postService.registChallengePost(post);
        return ResponseEntity.created(URI.create("post/challenge/")).build();
    }
}
