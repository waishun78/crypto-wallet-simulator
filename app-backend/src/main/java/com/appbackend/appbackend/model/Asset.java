package com.appbackend.appbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assets")
public class Asset implements DomainEntity<Long>, Serializable {
    @Id
    @Column(name = "assetId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long assetId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    private String cryptoId;
    private String cryptoName;
    private Double quantity;

    @JsonIgnore
    @Override
    public Long getId() {
        return this.assetId;
    }

    public boolean isEqualTo(Asset assetInRepo) {
        if (this.assetId == assetInRepo.getAssetId() &&
                this.quantity == assetInRepo.getQuantity() &&
                this.account == assetInRepo.getAccount() &&
                this.cryptoId == assetInRepo.getCryptoId() &&
                this.cryptoName == assetInRepo.getCryptoName()
        ) {
            return true;
        } else {
            return false;
        }
    }
}
