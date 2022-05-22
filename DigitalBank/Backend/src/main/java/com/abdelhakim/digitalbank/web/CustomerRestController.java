package com.abdelhakim.digitalbank.web;

import com.abdelhakim.digitalbank.DTOs.CustomerDTO;
import com.abdelhakim.digitalbank.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO customer(@PathVariable(name = "id") Long customerId) {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customer/{id}")
    public CustomerDTO updateCustomer(@PathVariable(name = "id") Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long customerId) {
        bankAccountService.deleteCustomer(customerId);
        return "Customer deleted successfully";
    }
}
