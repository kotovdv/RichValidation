package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;

import static com.jquartz.rich.validation.core.api.textual.Tokens.OPTIONAL;

/**
 * @author Dmitriy Kotov
 */
public class OptionalExpression<T> implements Expression<T> {
    @Override
    public TruthValue apply(T subject) {
        return TruthValue.TRUE;
    }

    @Override
    public String getTextualRepresentation() {
        return OPTIONAL.toString();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}