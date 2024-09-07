package org.northcoders.recordshopapi.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorPayload extends Payload {
    @JsonIgnore
    private RuntimeException error;

    @JsonProperty(value = "type")
    private final String errorType;

    @Builder
    public ErrorPayload(RuntimeException exception) {
        super(exception.getMessage());
        this.errorType = exception.getClass().getSimpleName();
//        this.error = exception;
    }
}
