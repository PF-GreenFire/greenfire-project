package sisosolsol.greenfire.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sisosolsol.greenfire.common.enums.like.LikeType;
import sisosolsol.greenfire.common.security.model.CustomUserDetails;
import sisosolsol.greenfire.like.model.dto.LikeDTO;
import sisosolsol.greenfire.like.service.LikeService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;
    
    // 좋아요 추가 및 삭제
    @PostMapping("/")
    public String toggleLike(
            @RequestBody LikeDTO likeDTO,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        //UUID userCode = user.getId(); TODO: 인증 에러 떠서 일단 가라로 수동 입력함. 추후 로그인 한 유저의 정보를 가져와 처리할 예정.
        //UUID userCode = UUID.fromString("7e52dc03-4e53-4360-b2d7-3e4fe6e56d32"); //테스트용 유저1
        UUID userCode = UUID.fromString("1205bf73-b5ca-460a-9317-8a5b5d06e784"); //테스트용 유저2

        return likeService.toggleLike(likeDTO, userCode);
    }

    // 좋아요 개수 조회
    @GetMapping("/{likeType}/{targetCode}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable LikeType likeType, @PathVariable int targetCode) {
        int likeCount = likeService.getLikeCount(likeType, targetCode);
        return ResponseEntity.ok(likeCount);
    }

}
