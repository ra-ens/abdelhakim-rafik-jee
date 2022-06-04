package com.abdelhakim.digitalbank;

import com.abdelhakim.digitalbank.DTOs.CustomerDTO;
import com.abdelhakim.digitalbank.entities.Customer;
import com.abdelhakim.digitalbank.security.entities.AppRole;
import com.abdelhakim.digitalbank.security.entities.AppUser;
import com.abdelhakim.digitalbank.security.services.AccountServiceImpl;
import com.abdelhakim.digitalbank.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    CommandLineRunner commandLineRunner(
            BankAccountService bankAccountService,
            AccountServiceImpl accountService
    ) {
        return args -> {

            // create roles
            Stream.of("ADMIN", "CUSTOMER").forEach(roleName -> {
                accountService.addNewRole(new AppRole(null, roleName));
            });

            // create users
            Stream.of("abdelhakim", "khalid", "jamila").forEach(name -> {
                AppUser user = new AppUser();
                user.setUsername(name);
                user.setPassword("123");
                accountService.addNewUser(user);

                // associate random role to this user
                accountService.addRoleToUser(name, Math.random() > 0.5 ? "ADMIN" : "CUSTOMER");
            });

            // create Customers
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
