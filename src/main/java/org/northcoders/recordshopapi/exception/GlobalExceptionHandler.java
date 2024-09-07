package org.northcoders.recordshopapi.exception;

import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.model.response.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorPayload> handleNotFoundException(NotFoundException exception) {
        ErrorPayload errorPayload = new ErrorPayload(exception);

        return new ResponseEntity<>(errorPayload, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorPayload> handleRuntimeException(RuntimeException exception) {
        ErrorPayload errorPayload = new ErrorPayload(exception);

        return new ResponseEntity<>(errorPayload, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
