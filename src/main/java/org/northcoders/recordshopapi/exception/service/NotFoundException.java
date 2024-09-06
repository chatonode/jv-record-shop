package org.northcoders.recordshopapi.exception.service;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Class<?> entityClass) {
        super(String.format("Entity '%s' not found.", entityClass.getSimpleName()));
    }
}
