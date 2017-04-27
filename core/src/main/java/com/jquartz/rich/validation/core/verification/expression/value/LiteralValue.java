package com.jquartz.rich.validation.core.verification.expression.value;

/**
 * @author Dmitriy Kotov
 */
public class LiteralValue<T extends Comparable<T>> implements Value<T> {

    private final T value;

    public LiteralValue(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }
}