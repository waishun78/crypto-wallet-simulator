package org.app.dao;

import org.app.domain.Account;
import org.app.domain.Portfolio;
import org.app.domain.Transaction;

import java.util.Collection;
import java.util.Date;

public interface TransactionRepository extends CrudRepository<Transaction> {

    // TODO: Need to block normal object creation for Transaction becuase it contains complex checking of money
    void addTransaction(Date dateRecord, String transactionType, Long cryptocurrencyID, Long transactionAmount, Long currencyQuantity, Account account, Portfolio portfolio);

    Collection<Transaction> findByAccount(Account account);
    Collection<Transaction> findByPortfolio(Portfolio portfolio);

    Collection<Transaction> findTransactionAmountGreaterThan(Long value);
    Collection<Transaction> findTransactionAmountLessThan(Long value);

    Collection<Transaction> findAllTransactionbyDate(Date startDate, Date endDate);
}
