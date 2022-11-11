package com.appbackend.appbackend.repository;


import com.appbackend.appbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // all crud database methods
}
