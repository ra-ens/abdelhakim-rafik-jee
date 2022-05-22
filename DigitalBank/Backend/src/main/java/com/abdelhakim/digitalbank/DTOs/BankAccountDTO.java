package com.abdelhakim.digitalbank.DTOs;

import com.abdelhakim.digitalbank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountDTO {
    private String id;
    private String type;
    private Double balance;
    private String currency;
    private AccountStatus status;
    private CustomerDTO customer;
    private Date CreatedAt;
}
