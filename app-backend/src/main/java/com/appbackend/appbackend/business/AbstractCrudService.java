package com.appbackend.appbackend.business;
import java.util.Optional;

import com.appbackend.appbackend.exception.EntityStateException;
import org.springframework.data.jpa.repository.JpaRepository;
import com.appbackend.appbackend.model.DomainEntity;

/**
 * Common superclass for business logic of all entities supporting operations Create, Read, Update, Delete.
 *
 * @param <ID> Type of (primary) key.
 * @param <E> Type of entity
 */
public abstract class AbstractCrudService<E extends DomainEntity<ID>, ID> {
    /**
     * Reference to data (persistence) layer.
     */
    protected final JpaRepository<E, ID> repository;

    protected AbstractCrudService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    /**
     * Attempts to store a new entity. Throws exception if an entity with the same key is already stored.
     *
     * @param entity entity to be stored
     * @throws EntityStateException if an entity with the same key is already stored
     */
    public E create(E entity) throws IllegalArgumentException {
//        if (repository.existsById(entity.getId()))
//            throw new IllegalArgumentException("entity " + entity + " already exists");
        return repository.save(entity); //delegate call to data layer
    }

    public Optional<E> readById(ID id) {
        return repository.findById(id);
    }

    public Iterable<E> readAll() {
        return repository.findAll();
    }

    /**
     * Attempts to replace an already stored entity.
     *
     * @param entity the new state of the entity to be updated; the instance must contain a key value
     * @throws EntityStateException if the entity cannot be found
     */
    public E update(E entity) throws IllegalArgumentException {
        if (repository.existsById(entity.getId()))
            return repository.save(entity);
        else
            throw new IllegalArgumentException("entity " + entity + " does not exist exists");
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
