package org.northcoders.recordshopapi.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.northcoders.recordshopapi.validation.validators.NullOrNotEmptyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( {ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = NullOrNotEmptyValidator.class)
public @interface NullOrNotEmpty {
    String message() default "{javax.validation.constraints.NullOrNotEmpty.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}