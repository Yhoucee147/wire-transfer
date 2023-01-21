package com.me_cash.wire_transfer.dao;

import com.me_cash.wire_transfer.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AccountDao extends CrudRepository<Account, String> {
    Account findByAccountNumber(String accountNumber);
}
