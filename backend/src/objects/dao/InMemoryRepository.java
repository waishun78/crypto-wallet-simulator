package objects.dao;

import objects.DomainEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<T extends DomainEntity> {
    private Map<Long, T> database = new HashMap<>();

    /* CRUD */
    public T save(T e){
        database.put(e.getUid(), e);
        return e;
    }

    public boolean existsById(Long uid){
        return database.containsKey(uid);
    }

    public Optional<T> findById(Long uid){
        return Optional.ofNullable(database.get(uid));
    }
    public Collection<T> findAll(){
        return database.values();
    }
    public void deleteById(Long uid){
        database.remove(uid);
    }
}
