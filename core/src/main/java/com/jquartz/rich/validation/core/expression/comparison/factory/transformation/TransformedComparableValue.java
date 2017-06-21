package com.jquartz.rich.validation.core.expression.comparison.factory.transformation;

import com.jquartz.rich.validation.core.expression.comparison.value.ComparableValue;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class TransformedComparableValue<T, S> implements ComparableValue<T, S> {

    private final ComparableValue<?, S> initialValue;
    private final Transformation<T> transformationSequence;

    public TransformedComparableValue(ComparableValue<?, S> initialValue, Transformation<T> transformationSequence) {
        this.initialValue = initialValue;
        this.transformationSequence = transformationSequence;
    }

    @Override
    public T get(@Nonnull S source) {
        return transformationSequence.applyTransformation(initialValue.get(source));
    }
}