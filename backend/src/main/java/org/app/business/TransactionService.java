package org.app.business;

import org.app.dao.TransactionRepository;
import org.app.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class TransactionService extends AbstractCrudService<Transaction> {
    protected TransactionService(TransactionRepository repository) {
        super(repository);
    }

    /**
     * Read Transactions greater than value.
     * @param value value of transaction
     * @return a Collection of transactions with transaction greater than value by portfolio.
     */
    public Collection<Transaction> findTransactionAmountGreaterThan(Long value) {
        return ((TransactionRepository) repository).findTransactionAmountGreaterThan(value);
    }

    /**
     * Read Transactions less than value.
     * @param value value of transaction
     * @return a Collection of transactions with transaction less than value by portfolio.
     */
    public Collection<Transaction> findTransactionAmountLessThan(Long value) {
        return ((TransactionRepository) repository).findTransactionAmountLessThan(value);
    }

    /**
     * Read Transactions between time period.
     * @param startDate earliest transaction date to retrieve
     * @param endDate last transaction date to retrieve
     * @return a Collection of transactions that occured between the startDate and endDate
     * @implNote if startDate before date, return earliest possible value, endDate can before current date
     */
    public Collection<Transaction> findAllTransactionbyDate(Date startDate, Date endDate) {
        return ((TransactionRepository) repository).findAllTransactionbyDate(startDate, endDate);
    }

}
