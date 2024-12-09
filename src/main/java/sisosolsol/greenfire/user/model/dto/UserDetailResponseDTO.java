package sisosolsol.greenfire.user.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import sisosolsol.greenfire.common.enums.user.Gender;
import sisosolsol.greenfire.common.enums.user.UserStatus;
import sisosolsol.greenfire.common.security.model.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class UserDetailResponseDTO {

    private UUID userCode;
    private String nickname;
    private String name;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private Gender gender;
    private String phone;
    private UserStatus status;
    private UserRole role;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

}