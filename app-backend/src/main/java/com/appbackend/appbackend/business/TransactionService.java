 package com.appbackend.appbackend.business;

 import com.appbackend.appbackend.model.Asset;
 import com.appbackend.appbackend.model.Transaction;
 import com.appbackend.appbackend.repository.AssetRepository;
 import com.appbackend.appbackend.repository.TransactionRepository;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Optional;


 /**
  * Business logic operations related to Asset domain type.
  */
 @Service
 public class TransactionService extends AbstractCrudService<Transaction, Long> {
     public TransactionService(TransactionRepository transactionRepository) {
         super(transactionRepository);
     }
     @Transactional
     public Collection<Transaction> findTransactionByUsername(String username) {
         Collection<Transaction> fullList = repository.findAll();
         Collection<Transaction> filteredList = new ArrayList<>();
         for (Transaction t : fullList){
             if (t.getAccount().getUsername().equalsIgnoreCase(username)){
                 filteredList.add(t);
             }
         }
         return filteredList;
     }
     @Transactional
     public Transaction updateByID(Transaction transactionDetails, Long id){
         Optional<Transaction> optionalTransaction = repository.findById(id);
         Transaction transactionToUpdate = optionalTransaction.orElseThrow();
         if (transactionDetails.getQuantityTransacted() != null) {
             transactionToUpdate.setQuantityTransacted(transactionDetails.getQuantityTransacted());
         }
         if (transactionDetails.getCryptoName() != null) {
             transactionToUpdate.setCryptoName(transactionDetails.getCryptoName());
         }
         if (transactionDetails.getExchangeRate() != null) {
             transactionToUpdate.setExchangeRate(transactionDetails.getExchangeRate());
         }
         if (transactionDetails.getCryptoId() != null) {
             transactionToUpdate.setCryptoId(transactionDetails.getCryptoId());
         }
         if (transactionDetails.getAccount() != null){
             transactionToUpdate.setAccount(transactionDetails.getAccount());
         }
         this.update(transactionToUpdate);
         return transactionToUpdate;
     }
     @Transactional
     public Collection<Transaction> deleteTransactionByUsername(String username) {
         Collection<Transaction> fullList = repository.findAll();
         Collection<Transaction> removedList = new ArrayList<>();
         for (Transaction t : fullList){
             if (t.getAccount().getUsername().equalsIgnoreCase(username)){
                 repository.delete(t);
                 removedList.add(t);
             }
         }
         return removedList;
     }
 }

