package com.jquartz.rich.validation.core.expression.comparison.operator;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public interface Comparison {

    <T extends Comparable<T>> boolean apply(@Nonnull T leftValue, @Nonnull T rightValue);
}