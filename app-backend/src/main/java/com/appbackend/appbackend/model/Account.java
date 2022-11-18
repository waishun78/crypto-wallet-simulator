package com.appbackend.appbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

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

//    @OneToMany(mappedBy = "account")
//    private Collection<Asset> assets;
//    @OneToMany(mappedBy = "account")
//    private Collection<Snapshot> snapshots;
//    @OneToMany(mappedBy = "account")
//    private Collection<Transaction> transactions;

    public Account(String username){
        this.username = username;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return this.username;
    }
}
