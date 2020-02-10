package com.hellokoding.springboot.restful.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class TransactionalProductService {
    private final ProductRespository productRespository;

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRespository.findByName(name);
    }

    public Product save(Product stock) {
        return productRespository.save(stock);
    }

    @Transactional
    public void updateImplicitly(Long id, String name) {
        Product product = findById(id).get();
        product.setName(name);

        // productRespository.save(product);
    }

    @Transactional
    public void updateOnCondition(Long id, String name) {
        Product product = findById(id).get();
        product.setName(name);

        if (product.getPrice().compareTo(new BigDecimal("10")) == 0) {
            productRespository.save(product);
        }
    }

    @Transactional
    void updateImplicitlyNonPublic(Long id, String name) {
        Product product = findById(id).get();
        product.setName(name);
    }

    @Transactional
    public void updateWithThrowingRuntimeException(Long id, String name) {
        Product product = findById(id).get();
        product.setName(name);
        throw new MyRuntimeException();
    }

    static class MyRuntimeException extends RuntimeException {
    }

    @Transactional
    public void updateWithThrowingException(Long id, String name) throws Exception {
        Product product = findById(id).get();
        product.setName(name);
        throw new MyException();
    }

    static class MyException extends Exception {
    }

    @Transactional(noRollbackFor = MyException.class)
    public void updateWithNoRollbackFor(Long id, String name) throws Exception {
        Product product = findById(id).get();
        product.setName(name);
        throw new MyException();
    }
}
