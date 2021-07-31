package com.raju.libraryManagementSystem.controller.advice;


import com.raju.libraryManagementSystem.constants.ErrorCodes;
import com.raju.libraryManagementSystem.entity.ErrorResponse;
import com.raju.libraryManagementSystem.exception.ServerErrorException;
import com.raju.libraryManagementSystem.exception.UserErrorException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Iterator;

@Log4j2
@ControllerAdvice
@RestController
public class LibraryManagementSystemExceptionHandler {
    @Value("${messages.genericError}")
    private String genericErrorMessage;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ErrorResponse handleGenericException(Exception e) {
        log.error("Exception: [{}]", e.getMessage(), e);
        return new ErrorResponse(ErrorCodes.SERVER_ERROR.name(), genericErrorMessage);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Exception: [{}]", e.getMessage(), e);
        return new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.name(), "Request method '" + e.getMethod() + "' not supported for this url.");
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ErrorResponse handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("Exception: [{}]", e.getMessage(), e);
        return new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        StringBuilder message = new StringBuilder();
        exception.getBindingResult().getFieldErrors()
                .stream()
                .forEach(ex -> {
                    message.append("'" + ex.getField() + "': ");
                    message.append(ex.getDefaultMessage() + ".");
                });
        ErrorResponse er = new ErrorResponse(ErrorCodes.INVALID_PARAMETER.name(), message.toString());
        return er;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(ConstraintViolationException exception) {
        log.error(exception.getMessage(), exception);
        StringBuilder message = new StringBuilder();
        exception.getConstraintViolations()
                .stream()
                .forEach(ex -> {
                    Path.Node lastPath = null;
                    Iterator it = ex.getPropertyPath().iterator();
                    while (it.hasNext()) {
                        lastPath = (Path.Node) it.next();
                    }
                    message.append("'" + lastPath.getName() + "': ");
                    message.append(ex.getMessage() + ".");
                });
        ErrorResponse er = new ErrorResponse(ErrorCodes.INVALID_PARAMETER.name(), message.toString());
        return er;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(BindException exception) {
        log.error(exception.getMessage(), exception);
        StringBuilder message = new StringBuilder();
        exception.getBindingResult().getFieldErrors()
                .stream()
                .forEach(ex -> {
                    message.append("'" + ex.getField() + "': ");
                    message.append(ex.getDefaultMessage() + ".");
                });
        ErrorResponse er = new ErrorResponse(ErrorCodes.INVALID_PARAMETER.name(), message.toString());
        return er;
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(HttpMessageNotReadableException exception) {
        log.error("Exception " + exception.getMessage(), exception);
        log.error("Most Specific Exception", exception.getMostSpecificCause());
        return new ErrorResponse(ErrorCodes.INVALID_BODY.name(), ErrorCodes.INVALID_BODY.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(HttpMessageConversionException exception) {
        log.error("Exception " + exception.getMessage(), exception);
        log.error("Most Specific Exception", exception.getMostSpecificCause());

        return new ErrorResponse(ErrorCodes.INVALID_BODY.name(), ErrorCodes.INVALID_BODY.getMessage());
    }

    @ExceptionHandler(value = UserErrorException.class)
    public ErrorResponse handleUserError(UserErrorException e, HttpServletResponse response) {
        log.error("Exception: [{}]", e.getMessage());
        response.setStatus(e.getStatus().value());
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ServerErrorException.class)
    public ErrorResponse handleServerError(ServerErrorException e, HttpServletResponse response) {
        log.error("Exception: [{}]", e.getMessage(), e);
        response.setStatus(e.getStatus().value());
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

}
