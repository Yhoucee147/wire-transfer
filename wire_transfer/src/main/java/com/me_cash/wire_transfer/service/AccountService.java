package com.me_cash.wire_transfer.service;

import com.me_cash.wire_transfer.dao.AccountDao;
import com.me_cash.wire_transfer.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public Account createAccount(Account account){
        return accountDao.save(account);
    }

    public Account getAccount(String username) {
        Optional<Account> account = accountDao.findById(username);
        if(account.isPresent()) {
            return account.get();
        }
        return null;
    }

    public Account findByAccountNumber(String accoutNumber) {
        return accountDao.findByAccountNumber(accoutNumber);
    }

}
