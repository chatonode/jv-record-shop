package org.northcoders.recordshopapi.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public abstract class Payload {
    private final LocalDateTime timestamp;
    private final PayloadStatus status;
    private final String message;

    public Payload(PayloadStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
}
