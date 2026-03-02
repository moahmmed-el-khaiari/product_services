package com.restaurant.productservice.Dto;

import com.restaurant.productservice.enums.SizeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeRequestDTO {
    private SizeType size;
    private Double price;
}
