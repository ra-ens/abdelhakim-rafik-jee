package com.abdelhakim.digitalbank.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private int currentPage;
    private int totalPages;
    private int size;
    private BankAccountDTO bankAccount;
    private List<AccountOperationDTO> operations;
}
