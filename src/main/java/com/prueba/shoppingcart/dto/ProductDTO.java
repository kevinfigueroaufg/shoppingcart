package com.prueba.shoppingcart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}

