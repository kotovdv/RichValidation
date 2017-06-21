package com.jquartz.rich.validation.core.expression.comparison.value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public interface ComparableValue<T, S> {

    T get(@Nonnull S source);
}