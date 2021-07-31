package com.raju.libraryManagementSystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserErrorException extends RuntimeException {
    private static final long serialVersionUID = -4312636664440743375L;
    private final HttpStatus status;
    private final String code;

    public UserErrorException(final HttpStatus status, final String code, final String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public UserErrorException(final HttpStatus status, final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public UserErrorException(final String code, final String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.code = code;
    }

    public UserErrorException(final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.BAD_REQUEST;
        this.code = code;
    }

}
