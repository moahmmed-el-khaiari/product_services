package com.restaurant.productservice.service;


import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;
import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.enums.ProductCategory;
import com.restaurant.productservice.mapper.ProductMapper;
import com.restaurant.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl  implements  ProductService{

        private final ProductRepository productRepository;
        private final ProductMapper productMapper;


        @Override
        public ResponceProductDTO addProduct(RequestProductDTO requestProductDTO) {
            Product product = productMapper.Dto_to_Pro(requestProductDTO);
            Product product1 = productRepository.save(product);
            return productMapper.Product_to_Dto(product1);
        }


    @Override
    public List<ResponceProductDTO> getAllproducts() {
        List<Product> products = productRepository.findAll();
        List<ResponceProductDTO> responceProductDTOS = new ArrayList<>();
        for (Product product : products) {
            responceProductDTOS.add(productMapper.Product_to_Dto(product));
        }
        return  responceProductDTOS;
    }

    @Override
    public ResponceProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productMapper.Product_to_Dto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow();


        productRepository.delete(product);
    }

    @Override
    public ResponceProductDTO updateProduct(Long id, RequestProductDTO requestProductDTO) {
        Product nv_Product = productMapper.Dto_to_Pro(requestProductDTO);
        Product product = productRepository.findById(id).orElseThrow();

        if(nv_Product.getName() !=null) product.setName(nv_Product.getName());
        if(nv_Product.getCategory()!=null) product.setCategory(nv_Product.getCategory());
        if(nv_Product.getPrice() !=null) product.setPrice(nv_Product.getPrice());
        if(nv_Product.getAvailable() !=null) product.setAvailable(nv_Product.getAvailable());
        if (nv_Product.getDescription() != null) product.setDescription(nv_Product.getDescription());
        if (requestProductDTO.getImageUrl() != null) {
            // Supprimer ancienne image si existe


            product.setImageUrl(requestProductDTO.getImageUrl());
        }
        Product savedProduct1 = productRepository.save(product);
        return productMapper.Product_to_Dto(savedProduct1);
    }
    @Override
    public List<ResponceProductDTO> getProductsByCategory(ProductCategory category) {

        List<Product> products = productRepository
                .findByCategoryAndAvailableTrue(category);

        List<ResponceProductDTO> response = new ArrayList<>();

        for (Product product : products) {
            response.add(productMapper.Product_to_Dto(product));
        }

        return response;
    }
    @Override
    public List<ResponceProductDTO> searchProducts(String name) {

        List<Product> products =
                productRepository.findByNameContainingIgnoreCaseAndAvailableTrue(name);

        List<ResponceProductDTO> response = new ArrayList<>();

        for (Product product : products) {
            response.add(productMapper.Product_to_Dto(product));
        }

        return response;
    }
}
