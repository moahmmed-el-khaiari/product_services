package com.restaurant.productservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SauceResponseDTO {
    private Long id;
    private String name;
    private Double extraPrice;
}
