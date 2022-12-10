package com.appbackend.appbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements DomainEntity<String>, Serializable {
    @Id
    @Column(name = "username")
    private String username;
    private String notes;
    private Double accountBalance;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Asset> assets;
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Snapshot> snapshots;
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Transaction> transactions;

    public Account(String username){
        this.username = username;
    }

    @JsonIgnore
    @Override
    public String getId() {
        return this.username;
    }

    public boolean isEqualTo(Account account1) {
        if (this.username == account1.getUsername() &&
                this.accountBalance == account1.getAccountBalance() &&
                this.notes == account1.getNotes()){
            return true;
        } else {
            return false;
        }
    }
}
