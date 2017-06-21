package com.jquartz.rich.validation.core.exception.api;

public class ClassIsNotComparableException extends RichVerificationException {

    public ClassIsNotComparableException(Class<?> targetClass) {
        super(String.format("Class [%s] does not implement comparable interface", targetClass.getSimpleName()));
    }
}