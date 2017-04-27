package com.jquartz.rich.validation.core.verification.expression.value;

/**
 * @author Dmitriy Kotov
 */
public interface Value<T extends Comparable<T>> {

    T get();
}