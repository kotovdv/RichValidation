package com.jquartz.rich.validation.core.expression.comparison.factory.transformation.concrete;

import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.Transformation;
import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.TransformationLogic;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class NumberTransformer {

    public static <R extends Number> TransformationLogic<R> createTransformation(Function<Number, R> transformer, Class<R> resultingClass) {
        List<Function<Object, Object>> transformation = new LinkedList<>();

        transformation.add(value -> applyTransformation(transformer, value));

        return new TransformationLogic<>(
                new Transformation<>(resultingClass, transformation),
                new Transformation<>(resultingClass, transformation)
        );
    }

    private static <R extends Number> R applyTransformation(Function<Number, R> transformer, Object value) {
        return transformer.apply(Number.class.cast(value));
    }
}