package org.app.domain;

public class Account extends DomainEntity{
    private String username,notes;
    private long accountBalance;
    private Portfolio portfolio;

    public Account(String username, String notes, long accountBalance, Portfolio portfolio) {
        this.username = username;
        this.notes = notes;
        this.accountBalance = accountBalance;
        this.portfolio = portfolio;
    }

}
