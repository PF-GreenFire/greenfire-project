package sisosolsol.greenfire.common.exception;

import lombok.Getter;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;

@Getter
public class CustomException extends RuntimeException {

    private final int code;
    private final String message;

    public CustomException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}