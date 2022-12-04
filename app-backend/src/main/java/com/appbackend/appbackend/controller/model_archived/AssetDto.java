package com.appbackend.appbackend.controller.model_archived;

public class AssetDto {
    private Long assetId;
    private String account;
    private String cryptoId;
    private String cryptoName;
    private Double quantity;

    public AssetDto(Long assetId, String account, String cryptoId, String cryptoName, Double quantity) {
        this.assetId = assetId;
        this.account = account;
        this.cryptoId = cryptoId;
        this.cryptoName = cryptoName;
        this.quantity = quantity;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
