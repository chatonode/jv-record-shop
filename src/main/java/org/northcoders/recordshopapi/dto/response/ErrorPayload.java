package org.northcoders.recordshopapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorPayload extends Payload {
    @JsonProperty(value = "name")
    private final String errorName;

    @Builder
    public ErrorPayload(Exception exception) {
        super(PayloadStatus.ERROR, exception.getMessage());
        this.errorName = exception.getClass().getSimpleName();
    }
}
