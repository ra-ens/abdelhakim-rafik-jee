package com.abdelhakim.digitalbank.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class SavingAccount extends BankAccount{

    private double interestRate;
}
