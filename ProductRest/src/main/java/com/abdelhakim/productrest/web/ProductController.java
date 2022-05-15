package com.abdelhakim.productrest.web;

import com.abdelhakim.productrest.dtos.ProductDTO;
import com.abdelhakim.productrest.entities.Product;
import com.abdelhakim.productrest.mappers.CatalogMapper;
import com.abdelhakim.productrest.repositories.ProductRepository;
import com.abdelhakim.productrest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;
    private ProductService productService;
    private CatalogMapper catalogMapper;

    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/product/{id}")
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = catalogMapper.fromProductDTO(productDTO);
        Product saved = productRepository.save(product);
        return catalogMapper.fromProduct(saved);
    }

    public void deleteProduct(String id) {
        productService.delete(Long.valueOf(id));
    }
}
