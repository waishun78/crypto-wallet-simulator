package org.app.business;


/**
 * A checked exception indicating problem related to existence of an entity.
 */
public class EntityStateException extends IllegalArgumentException {
    public EntityStateException(String s) {
        super(s);
    }
}
