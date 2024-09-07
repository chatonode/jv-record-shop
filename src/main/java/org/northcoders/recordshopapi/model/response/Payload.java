package org.northcoders.recordshopapi.model.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public abstract class Payload {
    private final LocalDateTime timestamp;
    private final String message;

    public Payload(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}
