package com.abdelhakim.digitalbank.entities;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class CurrentAccount extends BankAccount{

    private double overDraft;
}
