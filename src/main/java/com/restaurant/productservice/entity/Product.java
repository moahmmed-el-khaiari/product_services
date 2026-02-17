package com.restaurant.productservice.entity;

import com.restaurant.productservice.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Enumerated(EnumType.STRING)
        private ProductCategory category;

        private Double price;

        private Boolean available;
        @Column(length = 1000)
        private String description;

        private String imageUrl;
}
