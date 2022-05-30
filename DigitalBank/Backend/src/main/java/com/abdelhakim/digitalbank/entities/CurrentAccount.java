package com.abdelhakim.digitalbank.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("current")
public class CurrentAccount extends BankAccount{

    private double overDraft;
}
