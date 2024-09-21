package org.northcoders.recordshopapi.dto.response.error.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Error {
    private String field;
    private String reason;
}
