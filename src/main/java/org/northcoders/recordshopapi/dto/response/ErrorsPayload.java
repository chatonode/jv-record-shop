package org.northcoders.recordshopapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter
public class ErrorsPayload extends Payload {
    @JsonProperty(value = "name")
    private final String errorName;

    private final List<Error> errors;

    @Builder
    public ErrorsPayload(MethodArgumentNotValidException exception) {
        super(PayloadStatus.ERROR, "Validation failed.");
        this.errorName = exception.getClass().getSimpleName();
        this.errors = exception.getAllErrors().stream().map(objectError -> Error.builder()
                                .defaultMessage(objectError.getDefaultMessage())
                                .privateObjectName(objectError.getObjectName())
//                .field(objectError.getCode())
//                .rejectedValue(objectError.)
                                .build()
                )
                .toList();
    }
}
