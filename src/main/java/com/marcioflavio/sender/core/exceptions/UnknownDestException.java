package com.marcioflavio.sender.core.exceptions;

public class UnknownDestException extends RuntimeException {
    public UnknownDestException(String message) {
        super(message);
    }

    public UnknownDestException(String message, Throwable cause) {
        super(message, cause);
    }
}
