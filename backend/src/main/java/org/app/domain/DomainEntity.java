package org.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class DomainEntity {
    @Id
    private Long uid;
    public Long getUid(){
        return uid;
    };

    @Override
    public int hashCode() { return getUid().hashCode();}
}
