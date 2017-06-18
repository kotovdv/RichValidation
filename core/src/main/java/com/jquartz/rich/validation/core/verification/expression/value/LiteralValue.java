package com.jquartz.rich.validation.core.verification.expression.value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class LiteralValue<T extends Comparable<T>> implements Value<T> {

    private final T value;

    public LiteralValue(T value) {
        this.value = value;
    }

    public static <T extends Comparable<T>> LiteralValue<T> literal(T value) {
        return new LiteralValue<>(value);
    }

    @Override
    public T get(@Nonnull T source) {
        return value;
    }

    @Override
    public String toString() {
        return "{" + value + '}';
    }
}