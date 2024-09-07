package org.northcoders.recordshopapi.exception.service;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> entityClass) {
        super(String.format("NotFound: %s", entityClass.getSimpleName()));
    }
}
