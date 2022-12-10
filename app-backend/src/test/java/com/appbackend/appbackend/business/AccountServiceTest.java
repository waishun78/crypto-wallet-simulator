package com.appbackend.appbackend.business;

import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void testCreateAccount(){
        Account account1 = new Account();
        account1.setUsername("test1");
        account1.setAccountBalance(10.0);
        account1.setNotes("test1 notes");

        Mockito.when(accountRepository.save(account1)).thenReturn(account1);

        assertThat(accountService.create(account1).isEqualTo(account1));
    }

    // Check that values not provided in post are not updated
    @Test
    public void testUpdateByID(){
        String username = "test1";
        Account account1Posted = new Account();
        account1Posted.setUsername("test1");
        account1Posted.setAccountBalance(12.0);
        // Notes is null

        Account account1InRepo = new Account();
        account1InRepo.setUsername("test1");
        account1InRepo.setAccountBalance(12.0);
        account1InRepo.setNotes("test1 notes");

        // In service.update()
        Mockito.when(accountRepository.existsById(username)).thenReturn(true);
        Mockito.when(accountRepository.save(account1InRepo)).thenReturn(account1InRepo);
        Mockito.when(accountRepository.findById(username)).thenReturn(Optional.of(account1InRepo));
        Mockito.when(accountService.update(account1InRepo)).thenReturn(account1InRepo);

        assertThat(accountService.updateByID(account1Posted,username).isEqualTo(account1InRepo));

    }

}