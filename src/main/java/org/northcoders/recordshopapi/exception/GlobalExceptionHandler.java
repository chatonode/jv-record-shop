package org.northcoders.recordshopapi.exception;

import org.northcoders.recordshopapi.dto.response.error.validation.ValidationErrorsPayload;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.dto.response.error.common.BasicErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsPayload> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        ValidationErrorsPayload validationErrorsPayload = new ValidationErrorsPayload(exception);

        return new ResponseEntity<>(validationErrorsPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BasicErrorPayload> handleNotFoundException(NotFoundException exception) {
        BasicErrorPayload basicErrorPayload = new BasicErrorPayload(exception, "Resource does not exist.");

        return new ResponseEntity<>(basicErrorPayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BasicErrorPayload> handleRuntimeException(RuntimeException exception) {
        BasicErrorPayload basicErrorPayload = new BasicErrorPayload(exception, "Generic Error");

        return new ResponseEntity<>(basicErrorPayload, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
