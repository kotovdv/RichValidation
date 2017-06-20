package com.jquartz.rich.validation.core.api;

public class LiteralPointer<T> {

    private final T value;

    public LiteralPointer(T value) {
        this.value = value;
    }

    public T resolve() {
        return value;
    }
}
