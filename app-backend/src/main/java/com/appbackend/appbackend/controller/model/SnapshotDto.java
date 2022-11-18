package com.appbackend.appbackend.controller.model;

import com.appbackend.appbackend.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

public class SnapshotDto {
    private Long snapshotId;
    private String account;

    private Double assetValue;
    private Double accountBalance;
    private Date snapshotTime;

    public SnapshotDto(Long snapshotId, String account, Double assetValue, Double accountBalance, Date snapshotTime) {
        this.snapshotId = snapshotId;
        this.account = account;
        this.assetValue = assetValue;
        this.accountBalance = accountBalance;
        this.snapshotTime = snapshotTime;
    }

    public Long getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Long snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(Double assetValue) {
        this.assetValue = assetValue;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }
}
