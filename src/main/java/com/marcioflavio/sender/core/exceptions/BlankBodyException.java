package com.marcioflavio.sender.core.exceptions;

public class BlankBodyException extends RuntimeException {
    public BlankBodyException(String message) {
        super(message);
    }

    public BlankBodyException(String message, Throwable cause) {
        super(message, cause);
    } 
}
