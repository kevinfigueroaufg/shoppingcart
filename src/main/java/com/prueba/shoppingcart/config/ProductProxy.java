package com.prueba.shoppingcart.config;

import com.prueba.shoppingcart.dto.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductProxy {

    private final RestTemplate restTemplate;

    public ProductProxy(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<ProductDTO> getProducts() {
        ProductDTO[] productos = restTemplate.getForObject("https://fakestoreapi.com/products", ProductDTO[].class);
        return Arrays.asList(productos);
    }

    public ProductDTO getProductById(Integer id) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductDTO.class);
    }
}