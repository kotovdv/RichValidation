package com.jquartz.rich.validation.core.exception.api;

/**
 * @author Dmitriy Kotov
 */
public class IncomparableClassesException extends RichValidationException {

    public IncomparableClassesException(Class<?> firstClass, Class<?> secondClass) {
        super(String.format("Unable to compare [%s] with [%s]", firstClass.getSimpleName(), secondClass.getSimpleName()));
    }
}