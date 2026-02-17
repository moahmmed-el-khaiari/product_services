package com.restaurant.productservice.web;
import com.restaurant.productservice.Dto.RequestMenuDTO;
import com.restaurant.productservice.Dto.ResponseMenuDTO;
import com.restaurant.productservice.service.MenuServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/menus")
public class MenuController {

        private final MenuServiceImpl menuService;

    public MenuController(MenuServiceImpl menuService) {
        this.menuService = menuService;
    }

    // ========================
        // CRUD
        // ========================

        @PostMapping
        public ResponseEntity<ResponseMenuDTO> add(@RequestBody RequestMenuDTO dto) {
            return ResponseEntity.ok(menuService.addMenu(dto));
        }

        @GetMapping
        public ResponseEntity<List<ResponseMenuDTO>> getAll() {
            return ResponseEntity.ok(menuService.getAllMenus());
        }

        @GetMapping("/{id}")
        public ResponseEntity<ResponseMenuDTO> getById(@PathVariable Long id) {
            return ResponseEntity.ok(menuService.getMenuById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ResponseMenuDTO> update(
                @PathVariable Long id,
                @RequestBody RequestMenuDTO dto) {

            return ResponseEntity.ok(menuService.updateMenu(id, dto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            menuService.deleteMenu(id);
            return ResponseEntity.ok().build();
        }

        // ========================
        // Recherche
        // ========================

        @GetMapping("/search")
        public ResponseEntity<List<ResponseMenuDTO>> search(
                @RequestParam String name) {

            return ResponseEntity.ok(menuService.searchMenus(name));
        }

        // ========================
        // Filtre disponible
        // ========================

        @GetMapping("/available")
        public ResponseEntity<List<ResponseMenuDTO>> getAvailable() {
            return ResponseEntity.ok(menuService.getAvailableMenus());
        }

        // ========================
        // Pagination
        // ========================

        @GetMapping("/page")
        public ResponseEntity<Page<ResponseMenuDTO>> getPaginated(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "5") int size) {

            return ResponseEntity.ok(
                    menuService.getMenusPaginated(page, size)
            );
        }
    }

