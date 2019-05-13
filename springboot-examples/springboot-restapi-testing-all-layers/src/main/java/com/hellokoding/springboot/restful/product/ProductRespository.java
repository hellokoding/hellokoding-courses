package com.hellokoding.springboot.restful.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
