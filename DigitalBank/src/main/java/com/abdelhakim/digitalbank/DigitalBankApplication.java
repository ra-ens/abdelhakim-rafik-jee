package com.abdelhakim.digitalbank;

import com.abdelhakim.digitalbank.DTOs.CustomerDTO;
import com.abdelhakim.digitalbank.entities.Customer;
import com.abdelhakim.digitalbank.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Mohammed", "Amina", "Adil").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setFirstName(name);
                customer.setLastName(name);
                bankAccountService.saveCustomer(customer);
            });

          // create bank accounts
          bankAccountService.listCustomers().forEach(customer -> {
              bankAccountService.saveCurrentBankAccount(Math.random() * 9000, customer.getId(), 9000);
              bankAccountService.saveSavingBankAccount(Math.random() * 120000, customer.getId(), 4.3);
          });

          bankAccountService.listBankAccounts().forEach(bankAccount -> {
              for(int i=0; i<10; i++) {
                  bankAccountService.credit(bankAccount.getId(), 1000 + Math.random() * 12000, "Credit");
                  bankAccountService.debit(bankAccount.getId(), 1000 + Math.random() * 5000, "Debit");
              }
          });
        };
    }

}
