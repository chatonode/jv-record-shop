package org.northcoders.recordshopapi.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public abstract class CustomResponse {
    private final LocalDateTime timestamp;
    private final int code;
    private final HttpStatus status;
    private final String message;

    public CustomResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.code = this.status.value();
        this.message = message;
    }
}
