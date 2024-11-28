package sisosolsol.greenfire.common.exception;

import lombok.Getter;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;

@Getter
public class BadRequestException extends CustomException {

    public BadRequestException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}