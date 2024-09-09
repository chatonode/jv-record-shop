package org.northcoders.recordshopapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Error {
    private String defaultMessage;
    private String privateObjectName;
//    private String field;
//    private String rejectedValue;
}
