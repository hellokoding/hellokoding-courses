package com.hellokoding.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductCodeExistingValidator implements ConstraintValidator<ProductCodeExisting, String> {
    @Override
    public boolean isValid(String productCode, ConstraintValidatorContext context) {
        return productCode.equals("P1") || productCode.equals("P2");
    }
}
