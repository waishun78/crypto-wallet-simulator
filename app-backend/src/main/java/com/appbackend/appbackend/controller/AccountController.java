package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.business.AccountService;
import com.appbackend.appbackend.business.AssetService;
import com.appbackend.appbackend.business.TransactionService;
import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    protected AccountService accountService;
    public TransactionService transactionService;
    public AssetService assetService;
    public AccountController(AccountService accountService, TransactionService transactionService, AssetService assetService) {
        this.accountService = accountService;
        this.assetService = assetService;
        this.transactionService = transactionService;
    }



    @GetMapping
    public List<Account> getAllAccounts(){
        return StreamSupport.stream(accountService.readAll().spliterator(), false).collect(Collectors.toList());
    }

    // create account REST API
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.create(account);
    }

    //get account by id REST API
    @GetMapping("{username}")
    public ResponseEntity<Account> getAccountById(@PathVariable String username){
        Account account = accountService.readById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(account);
    }

    //update account REST API
    @PutMapping("{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody Account accountDetails){
        try{
            Account accountToUpdate = accountService.updateByID(accountDetails, username);
            return ResponseEntity.ok(accountToUpdate);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete account REST API
    @DeleteMapping("{username}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String username){
        assetService.deleteAssetByUsername(username);
        transactionService.deleteTransactionByUsername(username);
        accountService.deleteById(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
