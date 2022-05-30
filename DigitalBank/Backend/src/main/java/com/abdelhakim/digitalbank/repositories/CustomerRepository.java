package com.abdelhakim.digitalbank.repositories;

import com.abdelhakim.digitalbank.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.firstName like :kw or c.lastName like :kw")
    List<Customer> searchCustomers(@Param("kw") String keyword);
}
