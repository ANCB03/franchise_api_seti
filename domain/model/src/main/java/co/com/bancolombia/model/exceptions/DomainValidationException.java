package co.com.bancolombia.model.exceptions;

import lombok.Getter;

@Getter
public class DomainValidationException extends RuntimeException {
    private final DomainErrorCode errorCode;

    public DomainValidationException(DomainErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DomainValidationException(DomainErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
    }

    public DomainValidationException(DomainErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public DomainValidationException(DomainErrorCode errorCode, String customMessage, Throwable cause) {
        super(customMessage, cause);
        this.errorCode = errorCode;
    }
}
