package com.restaurant.productservice.mapper;

import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;

import com.restaurant.productservice.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
         public Product Dto_to_Pro(RequestProductDTO requestProductDTO) {
             Product product = new Product();
             BeanUtils.copyProperties(requestProductDTO, product);
             return product;

         }
        public ResponceProductDTO Product_to_Dto(Product product) {
            ResponceProductDTO responceProductDTO = new ResponceProductDTO();
            BeanUtils.copyProperties(product,responceProductDTO);
            return responceProductDTO;


         }

}
