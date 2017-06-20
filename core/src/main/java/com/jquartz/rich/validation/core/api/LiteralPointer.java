package com.jquartz.rich.validation.core.api;

import java.util.Optional;

public class LiteralPointer<T> {

    private final T value;

    public LiteralPointer(T value) {
        this.value = value;
    }

    public T resolve() {
        return value;
    }

    public Optional<Class<?>> getTargetClass() {
        return value != null
                ? Optional.of(value.getClass())
                : Optional.empty();
    }
}
