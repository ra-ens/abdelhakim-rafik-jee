package com.abdelhakim.digitalbank.DTOs;

import com.abdelhakim.digitalbank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentAccountDTO extends BankAccountDTO {
    private double overDraft;
}
