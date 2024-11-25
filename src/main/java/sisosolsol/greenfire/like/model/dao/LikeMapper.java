package sisosolsol.greenfire.like.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sisosolsol.greenfire.common.enums.like.LikeType;

import java.util.UUID;

@Mapper
public interface LikeMapper {
//    void saveStoreLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);
//    void saveChallengeLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);
//    void savePostLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);
//
//    void deleteStoreLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);
//    void deleteChallengeLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);
//    void deletePostLike(@Param("userCode") UUID userCode,@Param("type") LikeType type,@Param("typeCode") int typeCode);

    // 좋아요 여부 확인
    boolean isAlreadyLiked(@Param("userCode") UUID userCode, @Param("type") LikeType type, @Param("targetCode") Integer targetCode);

    // 좋아요 삭제
    void deleteLike(@Param("userCode") UUID userCode, @Param("type") LikeType type, @Param("targetCode") Integer targetCode);

    // 좋아요 추기
    void insertLike(@Param("userCode") UUID userCode, @Param("type") LikeType type, @Param("targetCode") Integer targetCode);
}
