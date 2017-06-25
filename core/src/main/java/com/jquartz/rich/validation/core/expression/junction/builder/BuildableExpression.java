package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public interface BuildableExpression<T> {

    Expression<T> build();

    boolean isEmpty();

    void clear();
}
