package org.app.domain;

import javax.persistence.Entity;

public class Account extends DomainEntity{
    private String username,notes;
    private long accountBalance;
    private Portfolio portfolio;

    public Account(String username, String notes, Long accountBalance, Portfolio portfolio) {
        this.username = username;
        this.notes = notes;
        this.accountBalance = accountBalance;
        this.portfolio = portfolio;
    }

    public Account() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getUsername() {
        return username;
    }

    public String getNotes() {
        return notes;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
