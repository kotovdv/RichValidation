package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public abstract class AbstractExpression<T> implements Expression<T> {

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
