package com.abdelhakim.productrest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private CategoryDTO category;
}
