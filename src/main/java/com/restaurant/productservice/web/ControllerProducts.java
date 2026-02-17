package com.restaurant.productservice.web;

import com.restaurant.productservice.Dto.RequestProductDTO;
import com.restaurant.productservice.Dto.ResponceProductDTO;
import com.restaurant.productservice.enums.ProductCategory;
import com.restaurant.productservice.service.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/v1/produits")
public class ControllerProducts {

    private final ProductServiceImpl productService;

    public ControllerProducts(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponceProductDTO> add(@RequestBody RequestProductDTO requestProductDTO) {
        ResponceProductDTO response = productService.addProduct(requestProductDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<ResponceProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAllproducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponceProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponceProductDTO> update(@PathVariable Long id,
                                                     @RequestBody RequestProductDTO requestProductDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, requestProductDTO));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ResponceProductDTO>> getByCategory(
            @PathVariable ProductCategory category) {

        return ResponseEntity.ok(
                productService.getProductsByCategory(category)
        );
    }
    @GetMapping("/search")
    public ResponseEntity<List<ResponceProductDTO>> search(
            @RequestParam String name) {

        return ResponseEntity.ok(productService.searchProducts(name));
    }

}
