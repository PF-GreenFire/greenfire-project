package sisosolsol.greenfire.common.exception;

import lombok.Getter;
import sisosolsol.greenfire.common.exception.type.ExceptionCode;

@Getter
public class NotFoundException extends CustomException {

    public NotFoundException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
