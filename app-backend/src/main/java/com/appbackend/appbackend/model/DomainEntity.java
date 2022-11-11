package com.appbackend.appbackend.model;

public interface DomainEntity<ID> {
    /**
     *
     * @return the primary key value of this instance
     */
    ID getId();
}
