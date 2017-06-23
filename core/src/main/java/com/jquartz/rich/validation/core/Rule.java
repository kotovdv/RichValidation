package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public class Rule<T> {

    private final Expression<T> expression;

    public Rule(Expression<T> expression) {
        this.expression = expression;
    }

    public TruthValue validate(T instance) {
        return expression.apply(instance);
    }
}