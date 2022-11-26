package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.business.AccountService;
import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Account;
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

    // @Autowired
    // private AccountRepository accountRepository;
    @Autowired
    protected AccountService accountService;

    // @GetMapping
    // public List<Account> getAllAccounts(){
    //     return accountRepository.findAll();
    // }
    @GetMapping
    public List<Account> getAllAccounts(){
        return StreamSupport.stream(accountService.readAll().spliterator(), false).collect(Collectors.toList());
    }

    // create account REST API
    // TODO: Way to assign a username to the creation, instead of including the entire mapping into the account
    // @PostMapping
    // public Account createAccount(@RequestBody Account account){
    //     return accountRepository.save(account);
    // }
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.create(account);
    }

    //get account by id REST API
    // @GetMapping("{username}")
    // public ResponseEntity<Account> getAccountById(@PathVariable String username){
    //     Account account = accountRepository.findById(username)
    //             .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));
    //     return ResponseEntity.ok(account);
    // }
    @GetMapping("{username}")
    public ResponseEntity<Account> getAccountById(@PathVariable String username){
        Account account = accountService.readById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(account);
    }

    //update account REST API
    // @PutMapping("{username}")
    // public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody Account accountDetails){
    //     Account accountToUpdate = accountRepository.findById(username)
    //             .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));
    //     //TODO: Move this to service layer
    //     if (accountDetails.getAccountBalance() != null){
    //         accountToUpdate.setAccountBalance(accountDetails.getAccountBalance());
    //     }
    //     if (accountDetails.getNotes() != null){
    //         accountToUpdate.setNotes(accountDetails.getNotes());
    //     }
    //     accountRepository.save(accountToUpdate);
    //     return ResponseEntity.ok(accountToUpdate);
    // }
    @PutMapping("{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody Account accountDetails){
        try{
            Account accountToUpdate = accountService.readById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            if (accountDetails.getAccountBalance() != null){
                accountToUpdate.setAccountBalance(accountDetails.getAccountBalance());
            }
            if (accountDetails.getNotes() != null){
                accountToUpdate.setNotes(accountDetails.getNotes());
            }
            accountService.update(accountToUpdate);
            return ResponseEntity.ok(accountToUpdate);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete account REST API
    // @DeleteMapping("{username}")
    // public ResponseEntity<Account> deleteAccount(@PathVariable String username){
    //     Account account = accountRepository.findById(username)
    //             .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));

    //     accountRepository.delete(account);

    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // };
    @DeleteMapping("{username}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String username){
        accountService.deleteById(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
