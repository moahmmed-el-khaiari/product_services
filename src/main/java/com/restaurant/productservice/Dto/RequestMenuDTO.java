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

public class RequestMenuDTO {

    private String name;


    private List<Long> productIds;

    private Double price;

    private Boolean available;

    private String description;

    private String imageUrl;
}
