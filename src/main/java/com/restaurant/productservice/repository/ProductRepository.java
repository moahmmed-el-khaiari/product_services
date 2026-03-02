package com.restaurant.productservice.repository;


import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByNameContainingIgnoreCaseAndAvailableTrue(String name);
    @Query("""
   SELECT DISTINCT p
   FROM Product p
   LEFT JOIN FETCH p.sizes
""")
    List<Product> findAllWithDetails();

    @Query("""
   SELECT DISTINCT p
   FROM Product p
   LEFT JOIN FETCH p.sizes
   WHERE p.id = :id
""")
    Optional<Product> findByIdWithDetails(@Param("id") Long id);
    List<Product> findByCategory(ProductCategory category);
    List<Product> findByCategoryAndAvailableTrue(ProductCategory category);
    Page<Product> findByAvailableTrue(Pageable pageable);
}
