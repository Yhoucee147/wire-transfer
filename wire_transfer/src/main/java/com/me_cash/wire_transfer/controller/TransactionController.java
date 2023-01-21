package com.me_cash.wire_transfer.controller;

import com.me_cash.wire_transfer.model.TransactionDTO;
import com.me_cash.wire_transfer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getUserTransactionHistory")
    public ResponseEntity<?> getUserTransactionHistory(@RequestParam String accountNumber) {
        return new ResponseEntity<>(transactionService.getUserTransactionHistory(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/performTransaction")
    public ResponseEntity<?> performTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.performTransaction(transactionDTO);
            return new ResponseEntity<>("Transaction Performed Successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Transaction Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
