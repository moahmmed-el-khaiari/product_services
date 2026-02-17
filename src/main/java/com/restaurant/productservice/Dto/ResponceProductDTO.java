package com.restaurant.productservice.Dto;
import com.restaurant.productservice.enums.ProductCategory;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponceProductDTO {

    private Long id;

    private String name;

    @NotNull
    private ProductCategory category;

    private Double price;

    private Boolean available;


    private String description;

    private String imageUrl;
}
