package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public class ValidationLogic<T> {

    private final Expression<T> expression;

    public ValidationLogic(Expression<T> expression) {
        this.expression = expression;
    }

    public TruthValue verify(T instance) {
        return expression.apply(instance);
    }
}