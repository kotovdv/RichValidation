package com.jquartz.rich.validation.core.pointer.literal;

public class LiteralPointerFactory {

    @SuppressWarnings("unchecked")
    public <V> LiteralPointer<V> create(V value) {
        Class<V> type = value != null
                ? (Class<V>) value.getClass()
                : null;

        return new PlainLiteralPointer<>(value, type);
    }
}
