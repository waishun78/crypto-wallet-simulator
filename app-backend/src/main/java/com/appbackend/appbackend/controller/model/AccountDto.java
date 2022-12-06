package com.appbackend.appbackend.controller.model;

import java.util.Collection;

public class AccountDto {
    private String username;
    private String notes;
    private Double accountBalance;

    private Collection<Long> assets;
    private Collection<Long> snapshots;
    private Collection<Long> transactions;

    public AccountDto(String username, String notes, Double accountBalance, Collection<Long> assets, Collection<Long> snapshots, Collection<Long> transactions) {
        this.username = username;
        this.notes = notes;
        this.accountBalance = accountBalance;
        this.assets = assets;
        this.snapshots = snapshots;
        this.transactions = transactions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Collection<Long> getAssets() {
        return assets;
    }

    public void setAssets(Collection<Long> assets) {
        this.assets = assets;
    }

    public Collection<Long> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Collection<Long> snapshots) {
        this.snapshots = snapshots;
    }

    public Collection<Long> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Long> transactions) {
        this.transactions = transactions;
    }
}
