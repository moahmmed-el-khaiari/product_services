package com.restaurant.productservice.service;

import com.restaurant.productservice.Dto.SauceRequestDTO;
import com.restaurant.productservice.Dto.SauceResponseDTO;
import com.restaurant.productservice.entity.Product;
import com.restaurant.productservice.entity.Sauce;
import com.restaurant.productservice.mapper.SauceMapper;
import com.restaurant.productservice.repository.SauceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional

public class SauceServiceImpl implements SauceService{

    private final SauceRepository sauceRepository;
    private final SauceMapper sauceMapper;

    @Override
    public SauceResponseDTO create(SauceRequestDTO dto) {

        Sauce sauce = sauceMapper.Dto_to_sauce(dto);

        Sauce saved = sauceRepository.save(sauce);

        return sauceMapper.sauce_to_Dto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SauceResponseDTO> getAll() {

        return sauceRepository.findAll()
                .stream()
                .map(sauceMapper::sauce_to_Dto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SauceResponseDTO getById(Long id) {

        Sauce sauce = sauceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sauce not found with id: " + id));

        return sauceMapper.sauce_to_Dto(sauce);
    }

    @Override
    public SauceResponseDTO update(Long id, SauceRequestDTO dto) {

        Sauce sauce = sauceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sauce not found with id: " + id));

        sauce.setName(dto.getName());
        sauce.setExtraPrice(dto.getExtraPrice());

        Sauce updated = sauceRepository.save(sauce);

        return sauceMapper.sauce_to_Dto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Sauce sauce = sauceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sauce not found with id: " + id));

        // 🔥 Supprimer la relation dans les produits
        for (Product product : sauce.getProducts()) {
            product.getSauces().remove(sauce);
        }

        sauce.getProducts().clear();

        sauceRepository.delete(sauce);
    }

}
