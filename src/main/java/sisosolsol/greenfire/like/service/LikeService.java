package sisosolsol.greenfire.like.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisosolsol.greenfire.common.enums.like.LikeType;
import sisosolsol.greenfire.like.model.dao.LikeMapper;
import sisosolsol.greenfire.like.model.dto.LikeDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeService { // TODO: 등록, 취소 시 레디스 작업 실패시 예외 처리 예정 [DB랑 불일치 걱정]

    private final RedisTemplate<String, Object> redisTemplate;
    private final LikeMapper likeMapper;
    private  final Logger log = LoggerFactory.getLogger(LikeService.class);
//
//    // 좋아요 여부 체크
//    public boolean isLiked(UUID userCode, LikeType type, int typeCode) {
//        String userKey = "user_likes:" + userCode;
//        return redisTemplate.opsForSet().isMember(userKey, type + ":" + typeCode);
//    }
//
//    // 좋아요 취소
//    public void deleteLike(UUID userCode, LikeType type, int typeCode) {
//        String likeKey = "like_count:" + type + ":" + typeCode;
//        String userKey = "user_likes:" + userCode;
//        redisTemplate.opsForValue().decrement(likeKey);
//        redisTemplate.opsForSet().remove(userKey, type + ":" + typeCode);
//
////        switch (type) {
////            case STORE: likeMapper.deleteStoreLike(userCode, type, typeCode); break;
////            case CHALLENGE: likeMapper.deleteChallengeLike(userCode, type, typeCode); break;
////            case POST: likeMapper.deletePostLike(userCode, type, typeCode); break;
////        }
//
//        //likeMapper.deleteLike(userCode, type, typeCode);
//    }
//
//    // 좋아요 등록
//    public void registLike(UUID userCode, LikeType type, int typeCode) {
//        String likeKey = "like_count:" + type + ":" + typeCode;
//        String userKey = "user_likes:" + userCode;
//        redisTemplate.opsForValue().increment(likeKey);
//        redisTemplate.opsForSet().add(userKey, type + ":" + typeCode);
//
////        switch (type) {
////            case STORE: likeMapper.saveStoreLike(userCode, type, typeCode); break;
////            case CHALLENGE: likeMapper.saveChallengeLike(userCode, type, typeCode); break;
////            case POST: likeMapper.savePostLike(userCode, type, typeCode); break;
////        }
//
//        //likeMapper.saveLike(userCode, type, typeCode);
//    }
//
//    // 좋아요 등록 및 취소
//    @Transactional
//    public String toggleLike(LikeDTO likeDTO, UUID userCode) {
//        boolean isLiked = isLiked(userCode, likeDTO.getType(), likeDTO.get);
//
//        if(isLiked) { // 좋아요 등록 되어 있다면
//            deleteLike(userCode, type, typeCode);
//            return "좋아요 취소";
//        } else { // 좋아요 없다면
//            registLike(userCode, type, typeCode);
//            return "좋아요 등록";
//        }
//    }

//    public String toggleLike(LikeDTO likeDTO, UUID userCode) {
//        // 우선 현재 좋아요가 이미 있는지 확인
//        boolean isLiked = likeMapper.isAlreadyLiked(userCode, likeDTO.getType(), likeDTO.getTargetCode());
//
//        if (isLiked) {
//            // 이미 좋아요가 존재하면 좋아요 취소
//            likeMapper.deleteLike(userCode, likeDTO.getType(), likeDTO.getTargetCode());
//            return "좋아요가 취소되었습니다.";
//        } else {
//            // 좋아요가 존재하지 않으면 새로운 좋아요 추가
//            likeMapper.insertLike(userCode, likeDTO.getType(), likeDTO.getTargetCode());
//            return "좋아요가 추가되었습니다.";
//        }
//    }


    // 좋아요 추가 및 삭제 [테이블 저장 방식]
//    public String toggleLike(LikeDTO likeDTO, UUID userCode) {
//        // 이미 좋아요 되어 있는지 확인
//        boolean isAlreadyLiked = likeMapper.isAlreadyLiked(userCode, likeDTO.getType(), likeDTO.getTargetCode());
//
//        switch (likeDTO.getType()) {
//            case POST:
//                if (isAlreadyLiked) {
//                    likeMapper.deleteLike(userCode, LikeType.POST, likeDTO.getTargetCode());
//                    return "좋아요가 취소되었습니다.";
//                } else {
//                    likeMapper.insertLike(userCode, LikeType.POST, likeDTO.getTargetCode());
//                    return "좋아요가 추가되었습니다.";
//                }
//            case CHALLENGE:
//                if (isAlreadyLiked) {
//                    likeMapper.deleteLike(userCode, LikeType.CHALLENGE, likeDTO.getTargetCode());
//                    return "좋아요가 취소되었습니다.";
//                } else {
//                    likeMapper.insertLike(userCode, LikeType.CHALLENGE, likeDTO.getTargetCode());
//                    return "좋아요가 추가되었습니다.";
//                }
//            case STORE:
//                if (isAlreadyLiked) {
//                    likeMapper.deleteLike(userCode, LikeType.STORE, likeDTO.getTargetCode());
//                    return "좋아요가 취소되었습니다.";
//                } else {
//                    likeMapper.insertLike(userCode, LikeType.STORE, likeDTO.getTargetCode());
//                    return "좋아요가 추가되었습니다.";
//                }
//            default:
//                throw new IllegalArgumentException("지원하지 않는 좋아요 타입입니다.");
//        }
//    }


    // 좋아요 추가 및 삭제 [Redis 저장 방식]
    public String toggleLike(LikeDTO likeDTO, UUID userCode) {
        String redisKey = String.format("like:%s:%s:%d", userCode.toString(), likeDTO.getType().name(), likeDTO.getTargetCode());

        if (redisTemplate.hasKey(redisKey)) {
            // 이미 좋아요가 눌러져 있으면 삭제
            redisTemplate.delete(redisKey);
            log.info("레디스에서 좋아요 삭제됨. 키(like:회원번호:like타입:target번호): {}", redisKey);
            return "좋아요가 취소 되었습니다.";
        } else {
            // 좋아요 추가
            redisTemplate.opsForValue().set(redisKey, "1");
            log.info("레디스에서 좋아요 추가됨. 키(like:회원번호:like타입:target번호): {}", redisKey);
            return "좋아요가 추가 되었습니다.";
        }
    }


}
