package com.appbackend.appbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "snapshots")
public class Snapshot implements DomainEntity<Long>, Serializable {
    @Id
    @Column(name = "snapshotId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long snapshotId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    // Might be username
    private Account account;

    private Double assetValue;
    private Double accountBalance;

    @CreationTimestamp
    private Date snapshotTime;

    @JsonIgnore
    @Override
    public Long getId() {
        return this.snapshotId;
    }
}
