package com.hellokoding.springboot.restful.product;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ProductIDExistingValidator.class)
public @interface ProductIDExisting {
    String message() default "{ProductIDExisting}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
