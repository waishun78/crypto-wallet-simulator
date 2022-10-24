package org.app.dao;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T>  {

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity; will never be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    T save(T entity);

    /**
     * Retrieves an entity by its id.
     *
     * @param uid must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<T> findById(Long uid);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param uid must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    boolean existsById(Long uid);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Collection<T> findAll();

    /**
     * Deletes the entity with the given id.
     *
     * @param uid must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
    void deleteById(Long uid);

}
