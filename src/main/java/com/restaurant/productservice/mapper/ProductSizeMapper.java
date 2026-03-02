package com.restaurant.productservice.mapper;

import com.restaurant.productservice.Dto.ProductSizeRequestDTO;
import com.restaurant.productservice.Dto.ProductSizeResponseDTO;
import com.restaurant.productservice.entity.ProductSize;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeMapper {
    public ProductSize Dto_to_Pro_size(ProductSizeRequestDTO productSizeRequestDTO) {
        ProductSize product_size = new ProductSize();
        BeanUtils.copyProperties(productSizeRequestDTO, product_size);
        return product_size;

    }
    public ProductSizeResponseDTO Product_size_to_Dto(ProductSize product) {
        ProductSizeResponseDTO productSizeResponseDTO = new ProductSizeResponseDTO();
        BeanUtils.copyProperties(product,productSizeResponseDTO);
        return productSizeResponseDTO;
    }

}
