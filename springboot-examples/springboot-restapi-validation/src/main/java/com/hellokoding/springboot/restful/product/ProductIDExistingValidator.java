package com.hellokoding.springboot.restful.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class ProductIDExistingValidator implements ConstraintValidator<ProductIDExisting, Long> {
    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext context) {
        return Objects.isNull(productId) || productService.findById(productId).isPresent();
    }
}
