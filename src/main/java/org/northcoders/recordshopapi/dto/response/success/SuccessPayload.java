package org.northcoders.recordshopapi.dto.response.success;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.northcoders.recordshopapi.dto.response.Payload;
import org.northcoders.recordshopapi.dto.response.enums.PayloadStatus;
import org.northcoders.recordshopapi.dto.response.enums.SuccessResultType;

@Getter
public class SuccessPayload<T> extends Payload {
    @JsonIgnore
    private final SuccessResultType type;

    @JsonIgnore
    private final Class<?> entityClass;

    private final T data;

    @Builder
    public SuccessPayload(SuccessResultType successResultType, Class<?> entityClass, T data) {
        super(PayloadStatus.SUCCESS, "%s: %s".formatted(successResultType.name(), entityClass.getSimpleName()));
        this.type = successResultType;
        this.entityClass = entityClass;
        this.data = data;
    }
}
