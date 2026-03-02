package com.restaurant.productservice.mapper;


import com.restaurant.productservice.Dto.SauceRequestDTO;
import com.restaurant.productservice.Dto.SauceResponseDTO;
import com.restaurant.productservice.entity.Sauce;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SauceMapper {
    public Sauce Dto_to_sauce(SauceRequestDTO sauceRequestDTO) {
        Sauce sauce = new Sauce();
        BeanUtils.copyProperties(sauceRequestDTO, sauce);
        return sauce;

    }
    public SauceResponseDTO sauce_to_Dto(Sauce sauce) {
        SauceResponseDTO sauceResponseDTO = new SauceResponseDTO();
        BeanUtils.copyProperties(sauce,sauceResponseDTO);
        return sauceResponseDTO;
    }

}
