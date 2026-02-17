package com.restaurant.productservice.repository;


import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByNameContainingIgnoreCaseAndAvailableTrue(String name);

    List<Product> findByCategory(ProductCategory category);
    List<Product> findByCategoryAndAvailableTrue(ProductCategory category);
    Page<Product> findByAvailableTrue(Pageable pageable);
}
