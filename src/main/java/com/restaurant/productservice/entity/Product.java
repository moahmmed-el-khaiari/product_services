package com.restaurant.productservice.entity;

import com.restaurant.productservice.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Enumerated(EnumType.STRING)
        @Column(length = 50)
        private ProductCategory category;

        private Boolean available;

        @Column(length = 1000)
        private String description;

        private String imageUrl;

        @OneToMany(
                mappedBy = "product",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY
        )
        private List<ProductSize> sizes;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "product_sauces",
                joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "sauce_id")

        )
        private Set<Sauce> sauces = new HashSet<>();
}