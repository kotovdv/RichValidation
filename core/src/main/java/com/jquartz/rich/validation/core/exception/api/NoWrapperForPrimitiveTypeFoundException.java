package com.jquartz.rich.validation.core.exception.api;

/**
 * @author Dmitriy Kotov
 */
public class NoWrapperForPrimitiveTypeFoundException extends RichValidationException {

    public NoWrapperForPrimitiveTypeFoundException(Class<?> primitiveType) {
        super(String.format("Unable to find wrapper type for primitive [%s]", primitiveType.getSimpleName()));
    }
}