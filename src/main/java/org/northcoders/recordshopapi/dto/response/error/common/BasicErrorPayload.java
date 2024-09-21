package org.northcoders.recordshopapi.dto.response.error.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.northcoders.recordshopapi.dto.response.Payload;
import org.northcoders.recordshopapi.dto.response.enums.PayloadStatus;
import org.northcoders.recordshopapi.dto.response.error.ErrorPayload;

@Getter
public class BasicErrorPayload extends ErrorPayload<String> {

    public BasicErrorPayload(Exception exception, String detailedMessage) {
        super(exception.getMessage(), detailedMessage);
    }
}
