package com.hellokoding.java;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class Product {
    @NotNull
    private Long id;

    @NotNull
    @ProductCodeExisting
    private String code;

    @Size(min=1, max = 10, message = "{Size.name}")
    private String name;

    @Min(1)
    private BigDecimal price;

    public Product(Long id, String code, String name, BigDecimal price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
