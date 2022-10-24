package org.app.business;

import org.app.dao.TransactionRepository;
import org.app.domain.Transaction;

import java.util.NoSuchElementException;

public class TransactionService extends AbstractCrudService<Transaction> {
    protected TransactionService(TransactionRepository repository) {
        super(repository);
    }

    // TODO: Do I implement checking in DAO or business layer? DAO is atomic functions?
    /**
     * Add transaction.
     *
     * @param cryptocurrencyID id of cryptocurrency
     * @param username key of the user
     * @param portfolio key of the portfolio
     * @param transactionAmount amount being transacted
     * @param currencyQuantity quantity cryptocurrency being transacted
     * @throws NoSuchElementException if the specified post or user do not exist
     */

    /**
     * Read Transactions by Account.
     * @param uid uid of account
     * @return a Collection of transactions by account.
     */

    /**
     * Read Transactions by Portfolio.
     * @param uid uid of portfolio
     * @return a Collection of transactions by portfolio.
     */

    /**
     * Read Transactions greater than value.
     * @param value value of transaction
     * @return a Collection of transactions with transaction greater than value by portfolio.
     */

    /**
     * Read Transactions less than value.
     * @param value value of transaction
     * @return a Collection of transactions with transaction less than value by portfolio.
     */

    /**
     * Read Transactions less than value.
     * @param startDate earliest transaction date to retrieve
     * @param endDate last transaction date to retrieve
     * @return a Collection of transactions that occured between the startDate and endDate
     * @implNote if startDate before date, return earliest possible value, endDate can before current date
     */

}
