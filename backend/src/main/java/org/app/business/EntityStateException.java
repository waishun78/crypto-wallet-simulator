package org.app.business;


import org.springframework.stereotype.Service;

/**
 * A checked exception indicating problem related to existence of an entity.
 */
@Service
public class EntityStateException extends IllegalArgumentException {
    public EntityStateException(String s) {
        super(s);
    }

}
