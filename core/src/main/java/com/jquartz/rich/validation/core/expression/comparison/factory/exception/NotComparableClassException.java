package com.jquartz.rich.validation.core.expression.comparison.factory.exception;

import com.jquartz.rich.validation.core.exception.RichValidationException;

public class NotComparableClassException extends RichValidationException {

    public NotComparableClassException(Class<?> targetClass) {
        super(String.format("Class [%s] does not implement comparable interface", targetClass.getSimpleName()));
    }
}