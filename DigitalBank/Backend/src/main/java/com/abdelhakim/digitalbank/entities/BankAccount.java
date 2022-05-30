package com.abdelhakim.digitalbank.entities;

import com.abdelhakim.digitalbank.enums.AccountStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DiscriminatorColumn(name = "type")
public class BankAccount {

    @Id
    private String id;
    private Double balance;
    private Date CreatedAt;
    private String currency;
    private AccountStatus status;

    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;

}
