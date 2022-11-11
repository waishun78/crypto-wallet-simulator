package com.appbackend.appbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements DomainEntity<String> {
    @Id
    @Column(name = "username")
    private String username;
    private String notes;
    private Double accountBalance;


    public Account(String username){
        this.username = username;
    }


    @Override
    public String getId() {
        return this.username;
    }
}
