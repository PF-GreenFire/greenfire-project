package sisosolsol.greenfire.common.security.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupabaseUserDTO {
    private String id;
    private String email;
    private UserRole role;

    @Builder
    public SupabaseUserDTO(String id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role != null ? UserRole.fromAuthority(role) : UserRole.USER;
    }
}
