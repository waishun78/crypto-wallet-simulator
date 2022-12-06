package com.appbackend.appbackend.controller.model;

public class TransactionDto {

    private Long transactionId;
    private String account;
    private String cryptoId;
    private String cryptoName;
    private Double exchangeRate;
    private Double quantityTransacted;

    public TransactionDto(Long transactionId, String account, String cryptoId, String cryptoName, Double exchangeRate, Double quantityTransacted) {
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

    public String getCryptoId() {
        return cryptoId;
    }

    public void setCryptoId(String cryptoId) {
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
