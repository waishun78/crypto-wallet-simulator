package com.appbackend.appbackend.business;

import org.springframework.stereotype.Service;

import com.appbackend.appbackend.repository.AccountRepository;
import com.appbackend.appbackend.model.Account;

/**
 * Business logic operations related to Account domain type.
 */
@Service
public class AccountService extends AbstractCrudService<Account, String> {
    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
    }
}

