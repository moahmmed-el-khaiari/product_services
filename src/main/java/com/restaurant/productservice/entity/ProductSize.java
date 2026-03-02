package com.restaurant.productservice.entity;

import com.restaurant.productservice.enums.SizeType;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class ProductSize {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private SizeType size; // S, M, L

        private Double price; // prix selon taille

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

}
