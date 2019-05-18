package com.hellokoding.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Product product = new Product(null, "Hello Koding", "Coding Courses", null, new BigDecimal(0.789));
        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

        for(ConstraintViolation constraintViolation : constraintViolations) {
            String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
            System.out.println(fieldName + " " + constraintViolation.getMessage());
        }
    }
}
