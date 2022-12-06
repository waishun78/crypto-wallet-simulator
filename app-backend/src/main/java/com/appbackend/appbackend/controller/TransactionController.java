package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.business.TransactionService;
import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.model.Transaction;
import com.appbackend.appbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    protected TransactionService transactionService;

    @GetMapping
    public List<Transaction> getFilteredTransactionsByUsername(@RequestParam(value="username", required = false) String username){
        if (username == null){
            return StreamSupport.stream(transactionService.readAll().spliterator(), false).collect(Collectors.toList());
        } else {
            return StreamSupport.stream(transactionService.findTransactionByUsername(username).spliterator(), false).collect(Collectors.toList());
        }
    }

    @DeleteMapping
    public ResponseEntity<Collection<Transaction>> deleteByAccount(@RequestParam(value="username", required = false) String username){
        Collection<Transaction> deletedTransactions = transactionService.deleteTransactionByUsername(username);
        return new ResponseEntity<>(deletedTransactions, HttpStatus.ACCEPTED);
    }

    // create transaction REST API
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        try {
            Transaction createdTransaction = transactionService.create(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.ACCEPTED);
        } catch (InvalidDataAccessApiUsageException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //get transaction by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Transaction transaction = transactionService.readById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(transaction);
    }

    //update transaction REST API
    @PutMapping("{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails){
        try {
            Transaction transactionToUpdate = transactionService.updateByID(transactionDetails, id);
            return ResponseEntity.ok(transactionToUpdate);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }

    }

    // delete transaction REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id){
        transactionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    };
}
