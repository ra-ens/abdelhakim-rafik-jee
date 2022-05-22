package com.abdelhakim.digitalbank.repositories;

import com.abdelhakim.digitalbank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
