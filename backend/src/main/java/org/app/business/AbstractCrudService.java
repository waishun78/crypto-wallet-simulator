package org.app.business;

import org.app.dao.CrudRepository;
import org.app.domain.DomainEntity;

import java.util.Optional;

/**
 * Common superclass for business logic of all entities supporting operations Create, Read, Update, Delete.
 *
 * @param <Long> Type of (primary) key.
 * @param <E> Type of entity
 */
public abstract class AbstractCrudService<E extends DomainEntity> {
    /**
     * Reference to data (persistence) layer.
     */
    protected final CrudRepository<E> repository;

    protected AbstractCrudService(CrudRepository<E> repository) {
        this.repository = repository;
    }

    /**
     * Attempts to store a new entity. Throws exception if an entity with the same key is already stored.
     *
     * @param entity entity to be stored
     * @throws EntityStateException if an entity with the same key is already stored
     */
    public E create(E entity) throws EntityStateException {
        if (repository.existsById(entity.getUid()))
            throw new EntityStateException("entity " + entity + " already exists");
        return repository.save(entity); //delegate call to data layer
    }

    public Optional<E> readById(Long id) {
        return repository.findById((java.lang.Long) id);
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
    public E update(E entity) throws EntityStateException {
        if (repository.existsById(entity.getUid()))
            return repository.save(entity);
        else
            throw new EntityStateException("entity " + entity + " does not exist exists");
    }

    public void deleteById(Long id) {
        repository.deleteById((java.lang.Long) id);
    }
}
