package com.hellokoding.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(new Product(null, "Hello Koding", "Practical Coding Courses, Tutorials and Examples", BigDecimal.ZERO));

        for(ConstraintViolation constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getPropertyPath().toString().toUpperCase() + " " + constraintViolation.getMessage());
        }
    }
}
