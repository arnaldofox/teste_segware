package com.segware.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Classe VO para detalhar o erro do Exception
 * 
 * @author Arnaldo
 */
public class ErrorDetails {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final Date timestamp;
    private final String message;
    private final String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
