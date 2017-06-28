package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessStub;
import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public abstract class AbstractExpression<T> implements Expression<T> {

    @Override
    public final TruthValue apply(T subject) {
        return apply(subject, TrustworthinessStub.INSTANCE);
    }
}
