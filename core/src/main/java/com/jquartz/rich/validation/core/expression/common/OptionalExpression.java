package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public class OptionalExpression<T> implements Expression<T> {
    @Override
    public TruthValue apply(T subject) {
        return TruthValue.TRUE;
    }
}