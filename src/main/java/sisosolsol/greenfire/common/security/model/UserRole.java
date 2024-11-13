package sisosolsol.greenfire.common.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("일반회원"),
    ADMIN("관리자"),
    MANAGER("매니저");

    private final String description;
    private static final String ROLE_PREFIX = "ROLE_";

    public String getAuthority() {
        return ROLE_PREFIX + this.name();
    }

    public static UserRole fromAuthority(String authority) {
        if (authority == null) {
            return USER;
        }

        String role = authority.startsWith(ROLE_PREFIX)
                ? authority.substring(ROLE_PREFIX.length())
                : authority;

        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return USER;
        }
    }
}