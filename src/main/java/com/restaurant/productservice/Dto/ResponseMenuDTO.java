package com.restaurant.productservice.Dto;

import com.restaurant.productservice.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMenuDTO {
    private Long id;

    private String name;


    private List<Product> products;

    private Double price;

    private Boolean available;
    @Column(length = 1000)
    private String description;

    private String imageUrl;
}
