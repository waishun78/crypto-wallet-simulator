package org.app.business;

import org.app.dao.AccountRepository;
import org.app.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractCrudService<Account>{
    protected AccountService(AccountRepository repository) {
        super(repository);
    }

    // No particular business logic as most services can be done with CRUD service
    //TODO 2: Might add accountBalance update checker
}
