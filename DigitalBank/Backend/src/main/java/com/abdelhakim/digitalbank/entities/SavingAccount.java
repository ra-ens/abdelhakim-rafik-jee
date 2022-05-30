package com.abdelhakim.digitalbank.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("saving")
public class SavingAccount extends BankAccount{

    private double interestRate;
}
