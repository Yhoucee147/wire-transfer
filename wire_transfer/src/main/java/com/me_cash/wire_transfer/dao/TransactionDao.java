package com.me_cash.wire_transfer.dao;

import com.me_cash.wire_transfer.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByAccountNumber(String accountNumber);
}
