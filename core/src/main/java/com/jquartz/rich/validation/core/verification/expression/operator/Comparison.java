package com.jquartz.rich.validation.core.verification.expression.operator;

import com.jquartz.rich.validation.core.verification.expression.value.Value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public interface Comparison {

    <T extends Comparable<T>> boolean apply(@Nonnull Value<T> leftValue, @Nonnull Value<T> rightValue);
}