package sisosolsol.greenfire.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    InvalidForeignKeyException(1100, "외래 키 제약을 위반한 요청입니다.");

    private final int code;
    private final String message;
}
