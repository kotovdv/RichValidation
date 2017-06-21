package com.jquartz.rich.validation.core.exception.api;

public class FailedToExtractFieldValueException extends RichValidationException {

    public FailedToExtractFieldValueException(Class<?> sourceClass, String fieldName, Throwable cause) {
        super(String.format("Failed to extract value from class [%s] field [%s]",
                sourceClass.getSimpleName(),
                fieldName),
                cause);
    }
}
