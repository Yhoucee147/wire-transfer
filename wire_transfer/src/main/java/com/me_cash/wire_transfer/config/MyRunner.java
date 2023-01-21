package com.me_cash.wire_transfer.config;

import com.me_cash.wire_transfer.dao.AccountDao;
import com.me_cash.wire_transfer.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void run(String... args) throws Exception {
       Account account1 = accountDao.findByAccountNumber("1234567890");
       if(account1 == null) {
           Account newAccount1 = new Account();
           newAccount1.setAccountNumber("1234567890");
           newAccount1.setCurrency('A');
           newAccount1.setUsername("ugwuu@rocketmail.com");
           newAccount1.setBalance(10000.00);
           accountDao.save(newAccount1);
           System.out.println("Saved Account 1: " + newAccount1.toString());
       }
       Account account2 = accountDao.findByAccountNumber("6574839201");
       if(account2 == null) {
           Account newAccount2 = new Account();
           newAccount2.setAccountNumber("6574839201");
           newAccount2.setCurrency('B');
           newAccount2.setUsername("ugwuu2017@outlook.com");
           newAccount2.setBalance(10000.00);
           accountDao.save(newAccount2);
           System.out.println("Saved Account 2: " + newAccount2.toString());
       }
    }

}
