package com.jquartz.rich.validation.core.exception.api;

public class RichVerificationException extends RuntimeException {

    public RichVerificationException() {
    }

    public RichVerificationException(String message) {
        super(message);
    }

    public RichVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RichVerificationException(Throwable cause) {
        super(cause);
    }

    public RichVerificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
