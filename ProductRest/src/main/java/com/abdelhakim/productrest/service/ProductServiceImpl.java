package com.abdelhakim.productrest.service;

import com.abdelhakim.productrest.dtos.ProductDTO;
import com.abdelhakim.productrest.entities.Product;
import com.abdelhakim.productrest.mappers.CatalogMapper;
import com.abdelhakim.productrest.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CatalogMapper catalogMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = catalogMapper.fromProductDTO(productDTO);
        Product savedProduct = productRepository.save(product);
        return catalogMapper.fromProduct(savedProduct);
    }

    @Override
    public List<ProductDTO> listProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream().map(p -> catalogMapper.fromProduct(p)).collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
