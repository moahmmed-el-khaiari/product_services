package com.restaurant.productservice.service;
import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;
import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.entity.ProductSize;
import com.restaurant.productservice.entity.Sauce;
import com.restaurant.productservice.enums.ProductCategory;
import com.restaurant.productservice.enums.SizeType;
import com.restaurant.productservice.mapper.ProductMapper;
import com.restaurant.productservice.repository.ProductRepository;
import com.restaurant.productservice.repository.SauceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl  implements  ProductService{

        private final ProductRepository productRepository;
        private final ProductMapper productMapper;
        private final SauceRepository sauceRepository;

    private void validateSaucesByCategory(ProductCategory category, List<Long> sauceIds) {

        if (sauceIds == null || sauceIds.isEmpty()) {
            return;
        }

        if (category == ProductCategory.DRINK ||
                category == ProductCategory.DESSERT) {

            throw new IllegalArgumentException(
                    category + " ne peut pas avoir de sauces"
            );
        }
    }

    @Override
    public ResponceProductDTO addProduct(RequestProductDTO dto) {

        validateSaucesByCategory(dto.getCategory(), dto.getSauceIds());

        Product product = productMapper.Dto_to_Pro(dto);

        if (dto.getSizes() != null && !dto.getSizes().isEmpty()) {

            Map<SizeType, ProductSize> uniqueSizes = dto.getSizes()
                    .stream()
                    .collect(Collectors.toMap(
                            s -> s.getSize(),
                            s -> {
                                ProductSize size = new ProductSize();
                                size.setSize(s.getSize());
                                size.setPrice(s.getPrice());
                                size.setProduct(product);
                                return size;
                            },
                            (existing, replacement) -> existing
                    ));

            product.setSizes(new ArrayList<>(uniqueSizes.values()));
        }

        if (dto.getSauceIds() != null && !dto.getSauceIds().isEmpty()) {

            Set<Sauce> sauces = new HashSet<>(
                    sauceRepository.findAllById(dto.getSauceIds())
            );

            product.setSauces(sauces);
        }

        Product saved = productRepository.save(product);

        return productMapper.Product_to_Dto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<ResponceProductDTO> getAllproducts() {

        return productRepository.findAllWithDetails()
                .stream()
                .map(productMapper::Product_to_Dto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponceProductDTO getProductById(Long id) {

        Product product = productRepository
                .findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

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

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 🔥 Déterminer la catégorie finale (ancienne ou nouvelle)
        ProductCategory finalCategory =
                requestProductDTO.getCategory() != null
                        ? requestProductDTO.getCategory()
                        : product.getCategory();

        // 🔥 Validation métier
        validateSaucesByCategory(finalCategory, requestProductDTO.getSauceIds());

        // 🔹 Mise à jour des champs simples
        if (requestProductDTO.getName() != null)
            product.setName(requestProductDTO.getName());

        if (requestProductDTO.getCategory() != null)
            product.setCategory(requestProductDTO.getCategory());

        if (requestProductDTO.getAvailable() != null)
            product.setAvailable(requestProductDTO.getAvailable());

        if (requestProductDTO.getDescription() != null)
            product.setDescription(requestProductDTO.getDescription());

        if (requestProductDTO.getImageUrl() != null)
            product.setImageUrl(requestProductDTO.getImageUrl());

        // 🔹 Mise à jour des sauces
        if (requestProductDTO.getSauceIds() != null) {

            Set<Sauce> sauces = new HashSet<>(
                    sauceRepository.findAllById(requestProductDTO.getSauceIds())
            );

            product.setSauces(sauces);
        }

        // 🔹 Mise à jour des tailles
        if (requestProductDTO.getSizes() != null) {

            Map<SizeType, ProductSize> existingSizes = product.getSizes()
                    .stream()
                    .collect(Collectors.toMap(ProductSize::getSize, s -> s));

            Set<SizeType> incomingSizes = requestProductDTO.getSizes()
                    .stream()
                    .map(s -> s.getSize())
                    .collect(Collectors.toSet());

            // 🔥 Supprimer les tailles absentes dans la requête
            product.getSizes().removeIf(size ->
                    !incomingSizes.contains(size.getSize())
            );

            for (var sizeDto : requestProductDTO.getSizes()) {

                ProductSize existing = existingSizes.get(sizeDto.getSize());

                if (existing != null) {
                    existing.setPrice(sizeDto.getPrice());
                } else {
                    ProductSize newSize = new ProductSize();
                    newSize.setSize(sizeDto.getSize());
                    newSize.setPrice(sizeDto.getPrice());
                    newSize.setProduct(product);
                    product.getSizes().add(newSize);
                }
            }
        }

        Product saved = productRepository.save(product);

        return productMapper.Product_to_Dto(saved);
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
