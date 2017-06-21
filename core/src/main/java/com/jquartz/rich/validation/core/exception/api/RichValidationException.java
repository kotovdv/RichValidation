package com.jquartz.rich.validation.core.exception.api;

public class RichValidationException extends RuntimeException {

    public RichValidationException() {
    }

    public RichValidationException(String message) {
        super(message);
    }

    public RichValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RichValidationException(Throwable cause) {
        super(cause);
    }

    public RichValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
