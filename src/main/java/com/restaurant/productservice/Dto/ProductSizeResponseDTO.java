package com.restaurant.productservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.restaurant.productservice.enums.SizeType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductSizeResponseDTO {
    private Long id;
    private SizeType size;
    private Double price;
}
