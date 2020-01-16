package com.hellokoding.springboot.product;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
public class Product {
    private Integer id;
    private String name;

    static void main() {

    }
}
