package com.appbackend.appbackend.business;

import com.appbackend.appbackend.exception.EntityStateException;
import org.springframework.stereotype.Service;

import com.appbackend.appbackend.repository.AccountRepository;
import com.appbackend.appbackend.model.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Business logic operations related to Account domain type.
 */
@Service
public class AccountService extends AbstractCrudService<Account, String> {
    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
    }

     @Transactional
     public Account updateByID(Account accountDetails, String username){

         Optional<Account> optionalAccount = repository.findById(username);
         Account accountToUpdate = optionalAccount.orElseThrow();

         if (accountDetails.getAccountBalance() != null){
             accountToUpdate.setAccountBalance(accountDetails.getAccountBalance());
         }
         if (accountDetails.getNotes() != null){
             accountToUpdate.setNotes(accountDetails.getNotes());
         }
         this.update(accountToUpdate);
         return accountToUpdate;
     }

}

