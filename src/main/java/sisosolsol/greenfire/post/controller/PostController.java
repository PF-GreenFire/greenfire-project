package sisosolsol.greenfire.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sisosolsol.greenfire.common.security.model.CustomUserDetails;
import sisosolsol.greenfire.post.model.dto.PostCreateDTO;
import sisosolsol.greenfire.post.model.dto.PostDTO;
import sisosolsol.greenfire.post.model.dto.PostUpdateDTO;
import sisosolsol.greenfire.post.model.dto.SimplePostDTO;
import sisosolsol.greenfire.post.service.PostService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{postCode}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer postCode) {
        PostDTO post = postService.getPost(postCode);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/challenge/{challengeCode}")
    public ResponseEntity<List<SimplePostDTO>> getChallengePostList(@PathVariable Integer challengeCode) {
        List<SimplePostDTO> postList = postService.getChallengePostList(challengeCode);
        return ResponseEntity.ok(postList);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/challenge")
    public ResponseEntity<Void> createChallengePost(@RequestBody PostCreateDTO post,
                                                    @AuthenticationPrincipal CustomUserDetails user) {
        int postCode = postService.registChallengePost(post, user.getId());
        return ResponseEntity.created(URI.create("post/" + postCode)).build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{postCode}")
    public ResponseEntity<PostUpdateDTO> updatePost(@PathVariable Integer postCode,
                                                    @RequestBody PostUpdateDTO post,
                                                    @AuthenticationPrincipal CustomUserDetails user) {
        postService.updatePost(postCode, user, post);
        return ResponseEntity.ok(post);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{postCode}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer postCode,
                                           @AuthenticationPrincipal CustomUserDetails user) {
        postService.deletePost(postCode, user);
        return ResponseEntity.noContent().build();
    }
}
