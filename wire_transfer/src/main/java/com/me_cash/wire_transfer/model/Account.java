package com.me_cash.wire_transfer.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="account")
public class Account {

    @Id
    @Length(min = 3, max = 35)
    private String username;
    @Column
    private String accountNumber;
    @Column
    private char currency;
    @Column
    private double balance;

}
