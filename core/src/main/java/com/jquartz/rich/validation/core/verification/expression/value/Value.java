package com.jquartz.rich.validation.core.verification.expression.value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public interface Value<T extends Comparable<T>> {

    T get(@Nonnull T source);
}