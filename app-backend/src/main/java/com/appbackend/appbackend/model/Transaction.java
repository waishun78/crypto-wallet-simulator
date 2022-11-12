package com.appbackend.appbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction implements DomainEntity<Long> {
    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    // Might be username
    private Account account;

    private Long cryptoId;
    private String cryptoName;
    // Cost of one cryptocurrency unit in USD
    private Double exchangeRate;
    // Can be positive for buying and negative for selling
    private Double quantityTransacted;

    @Override
    public Long getId() {
        return this.transactionId;
    }
}
