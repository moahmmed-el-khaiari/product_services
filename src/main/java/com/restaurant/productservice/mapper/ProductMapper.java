package com.restaurant.productservice.mapper;

import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;

import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.entity.Sauce;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ProductMapper {
        private final ProductSizeMapper productSizeMapper;
        public Product Dto_to_Pro(RequestProductDTO dto) {
            Product product = new Product();
            BeanUtils.copyProperties(dto, product);
            return product;
        }

        public ResponceProductDTO Product_to_Dto(Product product) {

            ResponceProductDTO dto = new ResponceProductDTO();

            // 1️⃣ Champs simples
            BeanUtils.copyProperties(product, dto);

            // 2️⃣ Mapper tailles
            if (product.getSizes() != null) {
                dto.setSizes(
                        product.getSizes()
                                .stream()
                                .map(productSizeMapper::Product_size_to_Dto)
                                .toList()
                );
            }

            // 3️⃣ Mapper sauces -> sauceIds
            if (product.getSauces() != null) {
                dto.setSauceIds(
                        product.getSauces()
                                .stream()
                                .map(Sauce::getId)
                                .toList()
                );
            }

            return dto;
        }
    }


