package org.app.domain;

public abstract class DomainEntity {
    //TODO: Generate Uid for each domain entity
    private Long uid;
    public Long getUid(){
        return uid;
    };

    @Override
    public int hashCode() { return getUid().hashCode();}
}
