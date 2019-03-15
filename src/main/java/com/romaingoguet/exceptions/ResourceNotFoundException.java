package com.romaingoguet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Resource not found exceptions.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    /**
     * Instantiates a new Resource not found exceptions.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
