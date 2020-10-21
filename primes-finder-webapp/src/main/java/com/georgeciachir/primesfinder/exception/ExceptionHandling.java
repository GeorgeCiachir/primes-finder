package com.georgeciachir.primesfinder.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
        log.error(ex);
        var bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleOther(RuntimeException ex, WebRequest request) throws Exception {
        log.error(ex);
        return super.handleException(ex, request);
    }
}
