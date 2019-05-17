package com.hellokoding.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ProductCodeExistingValidator.class)
public @interface ProductCodeExisting {
    String message() default "{ProductCodeExisting}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
