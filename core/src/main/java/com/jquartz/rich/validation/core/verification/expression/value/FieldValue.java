package com.jquartz.rich.validation.core.verification.expression.value;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class FieldValue<T extends Comparable<T>> implements Value<T> {

    private final Field field;

    public FieldValue(Field field) {
        this.field = field;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(@Nonnull T source) {
        try {
            return (T) field.get(source);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}