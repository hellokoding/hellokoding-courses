package com.hellokoding.springboot.restful.product;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOs(List<Product> products);

    Product toProduct(ProductDTO productDTO);
}
