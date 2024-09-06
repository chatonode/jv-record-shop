package org.northcoders.recordshopapi.model.response;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessResponse extends CustomResponse {
    private final Object data;
    private final OperationType operationType;

    @Builder
    public SuccessResponse(HttpStatus status, Class<?> entityClass, OperationType operationType, Object data) {
        super(status, "%s: %s".formatted(operationType, entityClass.getSimpleName()));
        this.operationType = operationType;
        this.data = data;
    }
}
