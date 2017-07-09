package com.jquartz.rich.validation.core.pointer.literal;

import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.Transformation;

import java.util.Optional;

public class TransformedLiteralPointer<T> implements LiteralPointer<T> {
    private final LiteralPointer<?> initialPointer;
    private final Transformation<T> transformationSequence;

    public TransformedLiteralPointer(LiteralPointer<?> initialPointer,
                                     Transformation<T> transformationSequence) {
        this.initialPointer = initialPointer;
        this.transformationSequence = transformationSequence;
    }

    @Override
    public T resolve() {
        return transformationSequence.applyTransformation(initialPointer.resolve());
    }

    @Override
    public Optional<Class<? extends T>> getPointedClass() {
        return initialPointer.getPointedClass().isPresent()
                ? Optional.of(transformationSequence.getResultingType())
                : Optional.empty();
    }

    @Override
    public String getTextualRepresentation() {
        return initialPointer.getTextualRepresentation();
    }
}
