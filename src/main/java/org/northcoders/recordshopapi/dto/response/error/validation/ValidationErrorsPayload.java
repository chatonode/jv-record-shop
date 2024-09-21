package org.northcoders.recordshopapi.dto.response.error.validation;

import lombok.Getter;
import org.northcoders.recordshopapi.dto.response.error.ErrorPayload;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Objects;

@Getter
public class ValidationErrorsPayload extends ErrorPayload<List<Error>> {

    public ValidationErrorsPayload(MethodArgumentNotValidException exception) {
        super(
                extractErrors(exception),
                "Invalid Fields"
        );

    }

    private static List<Error> extractErrors(MethodArgumentNotValidException exception) {
        return exception.getAllErrors().stream().map(objectError -> {
                    // Extracted responsible field
                    String field = Objects.requireNonNull(objectError.getCodes())[1].split("\\.")[1];
                    String reason = objectError.getDefaultMessage();

                    return new Error(field, reason);
                })
                .toList();
    }
}
