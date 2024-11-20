package sisosolsol.greenfire.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    /*
     * 401: Unauthorized
     * 403: Forbidden
     * 404: Not Found
     * 405: Method Not Allowed
     * 409: Conflict
     */

    // 401 Error
    UNAUTHORIZED(401, "인증 되지 않은 요청입니다."),

    // 403 Error
    ACCESS_DENIED(403, "허가 되지 않은 요청입니다."),

    // 409 Error
    CHALLENGE_NOT_FOUND(404, "존재하지 않는 챌린지입니다."),
    CHALLENGE_INVALID_STATUS(409, "모집 중인 챌린지만 참여 가능합니다."),
    CHALLENGE_ALREADY_STARTED(409, "이미 시작된 챌린지입니다."),
    CHALLENGE_ALREADY_PARTICIPATED(409, "이미 참여 중인 챌린지입니다."),
    CHALLENGE_FULL_CAPACITY(409, "모집 정원이 마감되었습니다."),

    InvalidForeignKeyException(1100, "외래 키 제약을 위반한 요청입니다.");;

    private final int code;
    private final String message;

}