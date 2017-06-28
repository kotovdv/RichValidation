package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.EmptyTrustworthiness;
import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public abstract class AbstractExpression<T> implements Expression<T> {

    @Override
    public TruthValue apply(T subject) {
        return apply(subject, EmptyTrustworthiness.INSTANCE);
    }

}
