package com.abdelhakim.productrest.repositories;

import com.abdelhakim.productrest.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    void deleteById(Long id);
}
