package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Transaction;
import com.appbackend.appbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    // create transaction REST API
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }

    //get transaction by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction %d does not exist","id")));
        return ResponseEntity.ok(transaction);
    }

    //update transaction REST API
    @PutMapping("{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails){
        Transaction transactionToUpdate = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Transaction %d does not exist","id")));
        transactionToUpdate.setQuantityTransacted(transactionDetails.getQuantityTransacted());
        transactionToUpdate.setCryptoId(transactionDetails.getCryptoId());
        transactionToUpdate.setExchangeRate(transactionDetails.getExchangeRate());

        transactionRepository.save(transactionToUpdate);

        return ResponseEntity.ok(transactionToUpdate);
    }

    // delete transaction REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id){
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %d does not exist","id")));

        transactionRepository.delete(transaction);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
