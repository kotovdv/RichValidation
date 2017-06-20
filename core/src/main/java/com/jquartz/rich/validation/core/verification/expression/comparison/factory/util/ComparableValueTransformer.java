package com.jquartz.rich.validation.core.verification.expression.comparison.factory.util;

import com.jquartz.rich.validation.core.verification.expression.comparison.value.ComparableValue;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class ComparableValueTransformer<T, S> implements ComparableValue<T, S> {

    private final ComparableValue<?, S> initialValue;
    private final Transformation<T> transformationSequence;

    public ComparableValueTransformer(ComparableValue<?, S> initialValue, Transformation<T> transformationSequence) {
        this.initialValue = initialValue;
        this.transformationSequence = transformationSequence;
    }

    @Override
    public T get(@Nonnull S source) {
        return transformationSequence.applyTransformation(initialValue.get(source));
    }
}