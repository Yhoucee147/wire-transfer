package com.me_cash.wire_transfer.model;

import lombok.Data;

@Data
public class TransactionDTO {
    private String fromAccount;
    private String toAccount;
    private double amount;
}
