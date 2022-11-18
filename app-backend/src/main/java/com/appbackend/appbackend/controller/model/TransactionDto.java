package com.appbackend.appbackend.controller.model;

import com.appbackend.appbackend.model.Account;

import javax.persistence.*;

public class TransactionDto {

    private Long transactionId;
    private String account;
    private Long cryptoId;
    private String cryptoName;
    private Double exchangeRate;
    private Double quantityTransacted;

    public TransactionDto(Long transactionId, String account, Long cryptoId, String cryptoName, Double exchangeRate, Double quantityTransacted) {
        this.transactionId = transactionId;
        this.account = account;
        this.cryptoId = cryptoId;
        this.cryptoName = cryptoName;
        this.exchangeRate = exchangeRate;
        this.quantityTransacted = quantityTransacted;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getCryptoId() {
        return cryptoId;
    }

    public void setCryptoId(Long cryptoId) {
        this.cryptoId = cryptoId;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getQuantityTransacted() {
        return quantityTransacted;
    }

    public void setQuantityTransacted(Double quantityTransacted) {
        this.quantityTransacted = quantityTransacted;
    }
}
