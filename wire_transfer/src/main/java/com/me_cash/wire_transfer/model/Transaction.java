package com.me_cash.wire_transfer.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table( name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @Column
    private String accountNumber;
    @Column
    private double transactionAmount;
    @Column
    private TransactionType transactionType;
    @Column
    private LocalDate transactionDate;


}
