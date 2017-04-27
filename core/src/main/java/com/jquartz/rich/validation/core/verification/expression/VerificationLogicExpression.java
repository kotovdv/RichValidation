package com.jquartz.rich.validation.core.verification.expression;

import com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.value.Value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogicExpression<T extends Comparable<T>> {

    private final Value<T> leftValue;
    private final Value<T> rightValue;
    private final ComparisonOperator comparisonOperator;

    public VerificationLogicExpression(Value<T> leftValue,
                                       Value<T> rightValue,
                                       ComparisonOperator comparisonOperator) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.comparisonOperator = comparisonOperator;
    }

    public void apply(@Nonnull T instance) {
        boolean result = comparisonOperator.apply(leftValue, rightValue);
    }
}