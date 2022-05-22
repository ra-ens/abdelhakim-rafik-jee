package com.abdelhakim.digitalbank.web;

import com.abdelhakim.digitalbank.DTOs.AccountHistoryDTO;
import com.abdelhakim.digitalbank.DTOs.AccountOperationDTO;
import com.abdelhakim.digitalbank.DTOs.BankAccountDTO;
import com.abdelhakim.digitalbank.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/accounts")
    public List<BankAccountDTO> listBankAccounts() {
        return bankAccountService.listBankAccounts();
    }

    @GetMapping("/account/{id}")
    public BankAccountDTO getBankAccount(@PathVariable(name = "id") String accountId) {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/account/{id}/operations")
    public AccountHistoryDTO getBankAccountOperations(
            @PathVariable(name = "id") String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}
