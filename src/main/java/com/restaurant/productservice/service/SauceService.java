package com.restaurant.productservice.service;

import com.restaurant.productservice.Dto.SauceRequestDTO;
import com.restaurant.productservice.Dto.SauceResponseDTO;

import java.util.List;

public interface SauceService {

    SauceResponseDTO create(SauceRequestDTO dto);

    List<SauceResponseDTO> getAll();

    SauceResponseDTO getById(Long id);

    SauceResponseDTO update(Long id, SauceRequestDTO dto);

    void delete(Long id);
}
