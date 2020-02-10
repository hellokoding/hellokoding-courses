package com.hellokoding.springboot.restful.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionalTest {
    @Autowired
    private TransactionalProductService productService;

    @Before
    public void setUp(){
        // given
        Product product = Product.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();

        productService.save(product);
    }

    @Test
    public void testUpdateImplicitly() {
        productService.updateImplicitly(1L, "updated");

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isPresent();
    }

    @Test
    public void testUpdateOnCondition() {
        productService.updateOnCondition(1L, "updated");

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isPresent();
    }

    @Test
    public void testUpdateImplicitlyNonPublic() {
        productService.updateImplicitlyNonPublic(1L, "updated");

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isNotPresent();
    }

    @Test
    public void testRollbackRuntimeException() {
        try {
            productService.updateWithThrowingRuntimeException(1L, "updated");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isNotPresent();
    }

    @Test
    public void testRollbackException() {
        try {
            productService.updateWithThrowingException(1L, "updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isPresent();
    }

    @Test
    public void testNoRollbackFor() {
        try {
            productService.updateWithNoRollbackFor(1L, "updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Optional<Product> updatedProduct = productService.findByName("updated");
        assertThat(updatedProduct).isPresent();
    }
}
