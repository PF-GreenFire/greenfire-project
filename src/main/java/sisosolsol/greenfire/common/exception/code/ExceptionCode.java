package sisosolsol.greenfire.common.exception.code;

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
    FAIL_TO_UPLOAD_FILE(401, "파일 저장에 실패하였습니다."),
    FAIL_TO_DELETE_FILE(401, "파일 삭제에 실패하였습니다."),
    UNAUTHORIZED(401, "인증 되지 않은 요청입니다."),

    // 403 Error
    ACCESS_DENIED(403, "허가 되지 않은 요청입니다."),

    // 404 Error
    NOT_FOUND_MEMBER_ID(404, "해당 아이디가 존재하지 않습니다."),

    // 409 Error
    ALREADY_EXIST_REVIEW(409, "해당 주문 건에 이미 작성 된 리뷰가 있습니다.");


    private final int code;
    private final String message;

}