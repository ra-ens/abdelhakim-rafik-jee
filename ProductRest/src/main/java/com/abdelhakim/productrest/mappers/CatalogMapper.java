package com.abdelhakim.productrest.mappers;

import com.abdelhakim.productrest.dtos.CategoryDTO;
import com.abdelhakim.productrest.dtos.ProductDTO;
import com.abdelhakim.productrest.entities.Category;
import com.abdelhakim.productrest.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CatalogMapper {
    public ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setCategory(this.fromCategory(product.getCategory()));
        return productDTO;
    }

    public CategoryDTO fromCategory(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    public Category fromCategoryDTO(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return category;
    }

    public Product fromProductDTO(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setCategory(this.fromCategoryDTO(productDTO.getCategory()));
        return product;
    }
 }
