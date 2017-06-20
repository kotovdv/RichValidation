package com.jquartz.rich.validation.core.verification.expression.comparison.value;

import com.jquartz.rich.validation.core.api.LiteralPointer;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class LiteralValue<T, S> implements ComparableValue<T, S> {

    private final LiteralPointer<T> value;

    public LiteralValue(LiteralPointer<T> value) {
        this.value = value;
    }

    @Override
    public T get(@Nonnull S source) {
        return value.resolve();
    }
}