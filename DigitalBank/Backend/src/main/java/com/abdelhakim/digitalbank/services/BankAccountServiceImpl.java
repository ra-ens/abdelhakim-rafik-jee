package com.abdelhakim.digitalbank.services;

import com.abdelhakim.digitalbank.DTOs.*;
import com.abdelhakim.digitalbank.entities.*;
import com.abdelhakim.digitalbank.enums.OperationType;
import com.abdelhakim.digitalbank.exceptions.BalanceNotSufficientException;
import com.abdelhakim.digitalbank.exceptions.BankAccountNotFoundException;
import com.abdelhakim.digitalbank.exceptions.CustomerNotFoundException;
import com.abdelhakim.digitalbank.mappers.BankAccountMapperImpl;
import com.abdelhakim.digitalbank.repositories.AccountOperationRepository;
import com.abdelhakim.digitalbank.repositories.BankAccountRepository;
import com.abdelhakim.digitalbank.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl bankAccountMapper;


    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> bankAccountMapper.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return bankAccountMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        customer = customerRepository.save(customer);
        return bankAccountMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = bankAccountMapper.fromCustomerDTO(customerDTO);
        customer = customerRepository.save(customer);
        return bankAccountMapper.fromCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<BankAccountDTO> listBankAccounts() {
        return bankAccountRepository.findAll().stream().map(bankAccount -> {
            if(bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankAccountMapper.fromSavingAccount(savingAccount);
            }
            else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankAccountMapper.fromCurrentAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        if(bankAccount instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankAccountMapper.fromSavingAccount(savingAccount);
        }
        else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankAccountMapper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public List<AccountOperationDTO> listAccountOperations(String accountId) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream().map(op -> bankAccountMapper.fromAccountOperation(op)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) {
        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> bankAccountMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setOperations(accountOperationDTOS);
        accountHistoryDTO.setBankAccount(this.getBankAccount(accountId));
        accountHistoryDTO.setSize(size);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, Long customerId, double overDraft) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) throw new CustomerNotFoundException("Customer not found");

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount savedAccount = bankAccountRepository.save(currentAccount);
        return bankAccountMapper.fromCurrentAccount(savedAccount);
    }

    @Override
    public SavingAccountDTO saveSavingBankAccount(double initialBalance, Long customerId, double interestRate) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null) throw new CustomerNotFoundException("Customer not found");

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        SavingAccount savedAccount =  bankAccountRepository.save(savingAccount);
        return bankAccountMapper.fromSavingAccount(savedAccount);
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));
        if(bankAccount.getBalance() < amount) throw new BalanceNotSufficientException("Balance not sufficient");

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDest, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Transfer to " + accountIdDest);
        credit(accountIdDest, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public void deleteBankAccount(String accountId) {
        bankAccountRepository.deleteById(accountId);
    }
}
