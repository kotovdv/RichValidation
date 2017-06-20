package com.jquartz.rich.validation.core.verification.expression.comparison;

import com.jquartz.rich.validation.core.verification.expression.Expression;
import com.jquartz.rich.validation.core.verification.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.comparison.value.ComparableValue;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonExpression<T extends Comparable<T>, S> implements Expression<S> {

    private final ComparableValue<T, S> left;
    private final ComparisonOperator operator;
    private final ComparableValue<T, S> right;

    public ComparisonExpression(ComparableValue<T, S> left,
                                ComparisonOperator operator,
                                ComparableValue<T, S> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public boolean apply(@Nonnull S subject) {
        return operator.apply(
                this.left.get(subject),
                this.right.get(subject)
        );
    }

    @Override
    public String toString() {
        return "ComparisonExpression{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                '}';
    }
}