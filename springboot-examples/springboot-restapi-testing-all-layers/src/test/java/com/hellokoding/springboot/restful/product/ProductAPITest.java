package com.hellokoding.springboot.restful.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void whenFindAll_thenReturnProductDTOList() throws Exception {
        // given
        ProductDTO productDTO = ProductDTO.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();
        List<ProductDTO> productDTOs = Arrays.asList(productDTO);

        doReturn(new ArrayList<>()).when(productService).findAll();
        doReturn(productDTOs).when(productMapper).toProductDTOs(any());

        // when + then
        this.mockMvc.perform(get("/api/v1/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name", is(productDTO.getName())));
    }

    @Test
    public void whenFindById_thenReturnProductDTO() throws Exception {
        // given
        ProductDTO productDTO = ProductDTO.builder()
            .name("P1")
            .description("P1 desc")
            .price(new BigDecimal("1"))
            .build();

        doReturn(Optional.of(new Product())).when(productService).findById(anyLong());
        doReturn(productDTO).when(productMapper).toProductDTO(any(Product.class));

        // when + then
        this.mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(productDTO.getName())));
    }
}
