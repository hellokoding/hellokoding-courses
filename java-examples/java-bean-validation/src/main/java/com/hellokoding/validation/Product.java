package com.hellokoding.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class Product {
    @NotNull
    private Long id;

    @ProductCodeExisting
    private String code;

    @Size(min=1, max = 10, message = "{Size.name}")
    private String name;

    @NotNull(message = "must be not null")
    private String description;

    @Min(value = 1, message = "{Min.price}")
    private BigDecimal price;

    public Product(Long id, String code, String name, String description, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
