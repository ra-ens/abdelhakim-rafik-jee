package com.abdelhakim.productrest.service;

import com.abdelhakim.productrest.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDTO);
    List<ProductDTO> listProduct();
    void delete(Long id);
}
