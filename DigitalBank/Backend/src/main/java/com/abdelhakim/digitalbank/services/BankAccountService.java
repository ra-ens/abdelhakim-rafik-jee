package com.abdelhakim.digitalbank.services;

import com.abdelhakim.digitalbank.DTOs.*;
import com.abdelhakim.digitalbank.entities.BankAccount;
import com.abdelhakim.digitalbank.entities.Customer;
import com.abdelhakim.digitalbank.exceptions.BalanceNotSufficientException;
import com.abdelhakim.digitalbank.exceptions.BankAccountNotFoundException;
import com.abdelhakim.digitalbank.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    List<CustomerDTO> searchCustomers(String keyword);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    CurrentAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft);

    SavingAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate);

    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    List<BankAccountDTO> listBankAccounts();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<AccountOperationDTO> listAccountOperations(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size);

    void deleteBankAccount(String accountId);
}
