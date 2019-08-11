package com.hellokoding.springboot.caching.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "test")
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @CachePut(key = "T(java.lang.String).format('%s-%s-%s', #root.target.Class.simpleName, #root.methodName, #product.name)")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
