package com.jquartz.rich.validation.core.pointer.literal;

import javax.annotation.Nullable;

public class LiteralPointerFactory {

    public <V> LiteralPointer<?> create(@Nullable V value) {
        return createLiteralPointer(value, value != null ? value.getClass() : null);
    }

    private <V> LiteralPointer<V> createLiteralPointer(V value, @Nullable Class<? extends V> valueType) {
        return new PlainLiteralPointer<>(value, valueType);
    }
}