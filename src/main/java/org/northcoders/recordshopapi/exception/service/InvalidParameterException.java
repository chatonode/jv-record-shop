package org.northcoders.recordshopapi.exception.service;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(Class<?> entityClass, String parameterName) {
        super(String.format("Invalid parameter '%s' provided for entity '%s'.", parameterName, entityClass.getSimpleName()));
    }
}
