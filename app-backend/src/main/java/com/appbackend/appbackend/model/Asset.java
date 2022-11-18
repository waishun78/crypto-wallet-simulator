package com.appbackend.appbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "assets")
public class Asset implements DomainEntity<Long> {
    @Id
    @Column(name = "assetId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long assetId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    // Might be username
    private Account account;

    private Long cryptoId;
    private String cryptoName;
    private Double quantity;

    @JsonIgnore
    @Override
    public Long getId() {
        return this.assetId;
    }
}
