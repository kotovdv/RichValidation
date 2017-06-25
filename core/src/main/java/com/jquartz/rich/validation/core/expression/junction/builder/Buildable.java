package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.expression.Expression;

/**
 * @author Dmitriy Kotov
 */
public interface Buildable<T> {

    Expression<T> build();

    boolean isEmpty();
}
