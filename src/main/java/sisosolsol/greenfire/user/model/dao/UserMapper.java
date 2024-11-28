package sisosolsol.greenfire.user.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sisosolsol.greenfire.user.model.dto.UserDTO;
import sisosolsol.greenfire.user.model.dto.UserUpdateDTO;

import java.util.UUID;

@Mapper
public interface UserMapper {

    // 회원 프로필 정보 조회
    UserDTO findByUserCode(UUID userCode);

    // 회원 프로필 정보 수정
    void updateUserProfile(@Param("userCode") UUID userCode, @Param("userDTO") UserUpdateDTO user);
}