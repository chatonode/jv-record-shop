package org.northcoders.recordshopapi.model.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse extends CustomResponse {
    //    private final Object data;
//    private final OperationType operationType;
    private final RuntimeException error;

    @Builder
    public ErrorResponse(HttpStatus status, RuntimeException exception) {
        super(status, exception.getMessage());
        this.error = exception;
    }
}
