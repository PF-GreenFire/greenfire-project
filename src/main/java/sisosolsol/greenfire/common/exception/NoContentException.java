package sisosolsol.greenfire.common.exception;

import lombok.Getter;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;

@Getter
public class NoContentException extends CustomException {

    public NoContentException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}