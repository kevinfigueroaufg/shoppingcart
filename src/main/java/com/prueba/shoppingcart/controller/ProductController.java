package com.prueba.shoppingcart.controller;

import com.prueba.shoppingcart.config.ProductProxy;
import com.prueba.shoppingcart.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin(maxAge = 3600)
public class ProductController {

    private final ProductProxy productClient;

    public ProductController(ProductProxy productClient) {
        this.productClient = productClient;
    }

    //Solo para probar el retorno de Fake Api
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return ResponseEntity.ok(productClient.getProducts());
    }

    //Solo para probar el retorno de Fake Api
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productClient.getProductById(id));
    }
}

