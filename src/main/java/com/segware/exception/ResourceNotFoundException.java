package com.segware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception de recursos não encontrados
 * 
 * @author Arnaldo
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = -231745733821618146L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
