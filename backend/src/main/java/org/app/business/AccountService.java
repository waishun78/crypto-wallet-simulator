package org.app.business;

import org.app.dao.AccountRepository;
import org.app.domain.Account;

public class AccountService extends AbstractCrudService<Account>{
    protected AccountService(AccountRepository repository) {
        super(repository);
    }
}
