package com.hellokoding.springboot.restful.product;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
