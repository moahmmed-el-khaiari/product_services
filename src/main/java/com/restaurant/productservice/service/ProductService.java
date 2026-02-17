package com.restaurant.productservice.service;

import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;
import com.restaurant.productservice.enums.ProductCategory;

import java.util.List;

public interface ProductService {
     ResponceProductDTO addProduct(RequestProductDTO requestProductDTO);
     List<ResponceProductDTO> getAllproducts();
     ResponceProductDTO getProductById(Long id);
     void deleteProduct(Long id);
     ResponceProductDTO updateProduct(Long id, RequestProductDTO requestProductDTO);
     List<ResponceProductDTO> getProductsByCategory(ProductCategory category);
    List<ResponceProductDTO> searchProducts(String name);

}
