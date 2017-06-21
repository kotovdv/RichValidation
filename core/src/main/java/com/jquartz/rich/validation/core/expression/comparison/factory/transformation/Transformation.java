package com.jquartz.rich.validation.core.expression.comparison.factory.transformation;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

/**
 * @author Dmitriy Kotov
 */
public class Transformation<T> {

    private final Class<T> resultingType;
    private final Queue<Function<Object, Object>> transformationQueue = new LinkedList<>();

    public Transformation(Class<T> resultingType) {
        this(resultingType, Collections.emptyList());
    }

    public Transformation(Class<T> resultingType, Collection<Function<Object, Object>> transformationApplied) {
        this.resultingType = resultingType;
        this.transformationQueue.addAll(transformationApplied != null ? transformationApplied : Collections.emptyList());
    }

    public <V> T applyTransformation(V initialValue) {
        Object currentValue = initialValue;
        for (Function<Object, Object> function : transformationQueue) {
            currentValue = function.apply(currentValue);
        }

        return resultingType.cast(currentValue);
    }

    public boolean isEmpty(){
        return transformationQueue.isEmpty();
    }
}