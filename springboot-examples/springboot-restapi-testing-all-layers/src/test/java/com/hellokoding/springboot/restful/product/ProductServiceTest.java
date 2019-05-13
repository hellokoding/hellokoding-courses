package com.hellokoding.springboot.restful.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRespository productRespository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void whenFindAll_thenReturnProductList() {
        // given
        Product product = Product.builder()
                .name("P1")
                .description("P1 desc")
                .price(new BigDecimal("1"))
                .build();
        List<Product> expectedProducts = Arrays.asList(product);

        doReturn(expectedProducts).when(productRespository).findAll();

        // when
        List<Product> actualProducts = productService.findAll();

        // then
        assertThat(actualProducts).isEqualTo(expectedProducts);
    }
}
