package com.jquartz.rich.validation.core.expression.comparison.factory.transformation;

import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class TransformedComparableValue<T extends Comparable<T>, S> implements Value<T, S> {
    private final Value<?, S> initialValue;
    private final Transformation<T> transformationSequence;

    public TransformedComparableValue(Value<?, S> initialValue, Transformation<T> transformationSequence) {
        this.initialValue = initialValue;
        this.transformationSequence = transformationSequence;
    }

    @Override
    public T get(@Nonnull S source) {
        return transformationSequence.applyTransformation(initialValue.get(source));
    }
}