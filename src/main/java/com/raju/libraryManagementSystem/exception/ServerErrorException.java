package com.raju.libraryManagementSystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServerErrorException extends RuntimeException {
    private static final long serialVersionUID = -4312636664440743385L;
    private final HttpStatus status;
    private final String code;

    public ServerErrorException(final HttpStatus status, final String code, final String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public ServerErrorException(final HttpStatus status, final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public ServerErrorException(final String code, final String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = code;
    }

    public ServerErrorException(final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = code;
    }

}
