package com.jquartz.rich.validation.core.pointer.literal;

import java.util.Objects;
import java.util.Optional;

public class PlainLiteralPointer<T> implements LiteralPointer<T> {

    private final T value;
    private final Class<T> pointedClass;

    public PlainLiteralPointer(T value, Class<T> pointedClass) {
        this.value = value;
        this.pointedClass = pointedClass;
    }

    public T resolve() {
        return value;
    }

    @Override
    public Optional<Class<T>> getPointedClass() {
        return Optional.ofNullable(pointedClass);
    }

    public String getTextualRepresentation() {
        return Objects.toString(value);
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
