package sisosolsol.greenfire.common.security.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupabaseUserDTO {
    private UUID id;
    private String email;
    private UserRole role;

    @Builder
    public SupabaseUserDTO(UUID id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role != null ? UserRole.fromAuthority(role) : UserRole.USER;
    }
}
