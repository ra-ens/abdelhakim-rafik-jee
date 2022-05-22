package com.abdelhakim.digitalbank.repositories;

import com.abdelhakim.digitalbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
