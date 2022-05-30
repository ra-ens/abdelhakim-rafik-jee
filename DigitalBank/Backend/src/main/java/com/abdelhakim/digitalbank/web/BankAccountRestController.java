package com.abdelhakim.digitalbank.web;

import com.abdelhakim.digitalbank.DTOs.*;
import com.abdelhakim.digitalbank.exceptions.BalanceNotSufficientException;
import com.abdelhakim.digitalbank.exceptions.BankAccountNotFoundException;
import com.abdelhakim.digitalbank.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
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

    @PostMapping("/account/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/account/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/account/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(
                transferDTO.getAccountSource(),
                transferDTO.getAccountDestination(),
                transferDTO.getAmount()
        );
    }
}
