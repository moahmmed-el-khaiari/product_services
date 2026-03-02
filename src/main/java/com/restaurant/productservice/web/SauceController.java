package com.restaurant.productservice.web;


import com.restaurant.productservice.Dto.SauceRequestDTO;
import com.restaurant.productservice.Dto.SauceResponseDTO;
import com.restaurant.productservice.service.SauceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sauces")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SauceController {


        private final SauceService sauceService;

        @PostMapping
        public ResponseEntity<SauceResponseDTO> create(
                @RequestBody SauceRequestDTO dto) {

            return ResponseEntity.ok(
                    sauceService.create(dto)
            );
        }

        @GetMapping
        public ResponseEntity<List<SauceResponseDTO>> getAll() {
            return ResponseEntity.ok(
                    sauceService.getAll()
            );
        }

        @GetMapping("/{id}")
        public ResponseEntity<SauceResponseDTO> getById(
                @PathVariable Long id) {

            return ResponseEntity.ok(
                    sauceService.getById(id)
            );
        }

        @PutMapping("/{id}")
        public ResponseEntity<SauceResponseDTO> update(
                @PathVariable Long id,
                @RequestBody SauceRequestDTO dto) {

            return ResponseEntity.ok(
                    sauceService.update(id, dto)
            );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(
                @PathVariable Long id) {

            sauceService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

