package com.abdelhakim.productrest;

import com.abdelhakim.productrest.entities.Category;
import com.abdelhakim.productrest.entities.Product;
import com.abdelhakim.productrest.repositories.CategoryRepository;
import com.abdelhakim.productrest.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class ProductRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductRestApplication.class, args);
    }

    @Bean
    CommandLineRunner start (
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        return args -> {
            // dummy categories
            Stream.of("Smart Phones", "Computer", "TV").forEach(cat -> {
                Category category = new Category();
                category.setName(cat);
                categoryRepository.save(category);
            });

            // dummy products
            categoryRepository.findAll().forEach(cat -> {
                for (int i = 0; i < 5; i++) {
                    Product product = new Product();
                    product.setName(cat.getName() + " " + (i + 1));
                    product.setPrice(100 + Math.random() * 500);
                    product.setQuantity((int) (5 + Math.random() * 50));
                    product.setCategory(cat);
                    cat.getProducts().add(product);
                    productRepository.save(product);
                }
            });
        };
    }
}
