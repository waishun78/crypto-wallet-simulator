package org.app.dao;

import org.app.domain.Account;
import org.app.domain.Portfolio;
import org.app.domain.Transaction;

import java.util.Collection;
import java.util.Date;

public interface TransactionRepository extends CrudRepository<Transaction> {

    Collection<Transaction> findByAccount(Account account);
    Collection<Transaction> findByPortfolio(Portfolio portfolio);

    Collection<Transaction> findTransactionAmountGreaterThan(Long value);
    Collection<Transaction> findTransactionAmountLessThan(Long value);

    Collection<Transaction> findAllTransactionbyDate(Date startDate, Date endDate);
}
