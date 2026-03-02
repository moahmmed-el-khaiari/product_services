package com.restaurant.productservice.repository;

import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long>{

    List<ProductSize> findByProductId (Long id);
}
