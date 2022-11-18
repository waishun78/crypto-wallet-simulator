package com.appbackend.appbackend.controller;

import com.appbackend.appbackend.exception.ResourceNotFoundException;
import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    // create account REST API
    // TODO: Way to assign a username to the creation, instead of including the entire mapping into the account
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    //get account by id REST API
    @GetMapping("{username}")
    public ResponseEntity<Account> getAccountById(@PathVariable String username){
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));
        return ResponseEntity.ok(account);
    }

    //update account REST API
    @PutMapping("{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody Account accountDetails){
        Account accountToUpdate = accountRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));
        if (accountDetails.getAccountBalance() != null){
            accountToUpdate.setAccountBalance(accountDetails.getAccountBalance());
        }
        if (accountDetails.getNotes() != null){
            accountToUpdate.setNotes(accountDetails.getNotes());
        }
        accountRepository.save(accountToUpdate);
        return ResponseEntity.ok(accountToUpdate);
    }

    // delete account REST API
    @DeleteMapping("{username}")
    public ResponseEntity<Account> deleteAccount(@PathVariable String username){
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee %s does not exist","username")));

        accountRepository.delete(account);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    };
}
