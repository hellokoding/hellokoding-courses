package com.hellokoding.springboot.restful.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRespository productRespository;

    @Before
    public void setUp(){
        // given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();

        testEntityManager.persist(product);
    }

    @Test
    public void whenFindByName_thenReturnProduct() {
        // when
        Product product = productRespository.findByName("P1").get();

        // then
        assertThat(product.getDescription()).isEqualTo("P1 desc");
    }

    @Test
    public void whenFindAll_thenReturnProductList() {
        // when
        List<Product> products = productRespository.findAll();

        // then
        assertThat(products).hasSize(1);
    }
}
