package org.northcoders.recordshopapi.dto.response.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.northcoders.recordshopapi.dto.response.Payload;
import org.northcoders.recordshopapi.dto.response.enums.PayloadStatus;

@Getter
public abstract class ErrorPayload<T> extends Payload {
    @JsonProperty(value = "error")
    private final T error;

    public ErrorPayload(T error, String detailedMessage) {
        super(PayloadStatus.ERROR, detailedMessage);
        this.error = error;
    }
}
