package com.appbackend.appbackend.repository;


import com.appbackend.appbackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    // all crud database methods
}
