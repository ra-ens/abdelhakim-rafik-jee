package com.abdelhakim.productrest.repositories;

import com.abdelhakim.productrest.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
