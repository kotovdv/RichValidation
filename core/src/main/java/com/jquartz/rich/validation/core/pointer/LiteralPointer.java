package com.jquartz.rich.validation.core.pointer;

import java.util.Objects;
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

    public String getTextualRepresentation() {
        return Objects.toString(value);
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
