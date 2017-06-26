package com.jquartz.rich.validation.core.pointer.field;

import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.Transformation;

public class TransformedFieldPointer<T, S> implements FieldPointer<T, S> {
    private final FieldPointer<?, S> initialPointer;
    private final Transformation<T> transformationSequence;

    public TransformedFieldPointer(FieldPointer<?, S> initialPointer,
                                   Transformation<T> transformationSequence) {
        this.initialPointer = initialPointer;
        this.transformationSequence = transformationSequence;
    }

    @Override
    public T resolve(S source) {
        return transformationSequence.applyTransformation(initialPointer.resolve(source));
    }

    @Override
    public Class<T> getPointedClass() {
        return transformationSequence.getResultingType();
    }

    @Override
    public String getTextualRepresentation() {
        return initialPointer.getTextualRepresentation();
    }
}
