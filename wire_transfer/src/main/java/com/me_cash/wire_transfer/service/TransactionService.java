package com.me_cash.wire_transfer.service;

import com.me_cash.wire_transfer.dao.AccountDao;
import com.me_cash.wire_transfer.dao.TransactionDao;
import com.me_cash.wire_transfer.model.Account;
import com.me_cash.wire_transfer.model.Transaction;
import com.me_cash.wire_transfer.model.TransactionDTO;
import com.me_cash.wire_transfer.model.TransactionType;
import com.me_cash.wire_transfer.util.WireTransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountDao accountDao;


    public Transaction recordTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getUserTransactionHistory(String accountNumber) {
        return transactionDao.findByAccountNumber(accountNumber);
    }

    public void performTransaction(TransactionDTO transactionDTO) throws Exception {
        System.out.println(transactionDTO.toString());
        Account fromAccount = accountDao.findByAccountNumber(transactionDTO.getFromAccount());
        if(fromAccount == null) {
            throw new Exception("No such account Exception for from Account");
        }
        Account toAccount = accountDao.findByAccountNumber(transactionDTO.getToAccount());
        if(toAccount == null) {
            throw new Exception("No such account Exception for to Account");
        }
        if(fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new Exception("Insufficient funds to perform transaction");
        }
        fromAccount.setBalance(fromAccount.getBalance() - transactionDTO.getAmount());
        accountDao.save(fromAccount);
        double creditAmount = 0.0;
        if(fromAccount.getCurrency() == toAccount.getCurrency()) {
            creditAmount = transactionDTO.getAmount();
            toAccount.setBalance(toAccount.getBalance() + creditAmount);
            accountDao.save(toAccount);
        } else {
            creditAmount = WireTransferUtil.convertCurrency(fromAccount.getCurrency(), transactionDTO.getAmount());
            toAccount.setBalance(toAccount.getBalance() + creditAmount);
            accountDao.save(toAccount);
        }
        Transaction tran1 = new Transaction();
        tran1.setTransactionDate(LocalDate.now());
        tran1.setTransactionType(TransactionType.DEBIT);
        tran1.setAccountNumber(fromAccount.getAccountNumber());
        tran1.setTransactionAmount(transactionDTO.getAmount());
        transactionDao.save(tran1);
        Transaction tran2 = new Transaction();
        tran2.setTransactionDate(LocalDate.now());
        tran2.setTransactionType(TransactionType.CREDIT);
        tran2.setAccountNumber(toAccount.getAccountNumber());
        tran2.setTransactionAmount(creditAmount);
        transactionDao.save(tran2);
    }

}
