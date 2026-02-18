package com.restaurant.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private Double price;

        private Boolean available;

        @Column(length = 1000)
        private String description;

        private String imageUrl;
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "menu_products",
                joinColumns = @JoinColumn(name = "menu_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id")
        )
        private List<Product> products;


}
