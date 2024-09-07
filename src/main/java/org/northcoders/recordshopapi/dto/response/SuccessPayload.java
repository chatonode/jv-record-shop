package org.northcoders.recordshopapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessPayload extends Payload {
    @JsonIgnore
    private final SuccessResultType type;

    @JsonIgnore
    private final Class<?> entityClass;

    private final Object data;

    @Builder
    public SuccessPayload(SuccessResultType successResultType, Class<?> entityClass, Object data) {
        super(PayloadStatus.SUCCESS, "%s: %s".formatted(successResultType.name(), entityClass.getSimpleName()));
        this.type = successResultType;
        this.entityClass = entityClass;
        this.data = data;
    }
}
