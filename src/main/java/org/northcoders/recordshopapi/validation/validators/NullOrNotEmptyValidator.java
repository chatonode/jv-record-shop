package org.northcoders.recordshopapi.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.northcoders.recordshopapi.validation.constraints.NullOrNotEmpty;

import java.util.ArrayList;

public class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, ArrayList<?>> {

    @Override
    public void initialize(NullOrNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ArrayList<?> objects, ConstraintValidatorContext constraintValidatorContext) {
        return objects == null || objects.size() > 1;
    }
}
