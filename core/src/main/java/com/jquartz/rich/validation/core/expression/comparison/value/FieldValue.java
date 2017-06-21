package com.jquartz.rich.validation.core.expression.comparison.value;

import com.jquartz.rich.validation.core.pointer.FieldPointer;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class FieldValue<T, S> implements ComparableValue<T, S> {

    private final FieldPointer<T, S> pointer;

    public FieldValue(FieldPointer<T, S> pointer) {
        this.pointer = pointer;
    }

    @Override
    public T get(@Nonnull S source) {
        return pointer.resolve(source);
    }
}