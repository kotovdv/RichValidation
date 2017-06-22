package com.jquartz.rich.validation.core.expression.comparison;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.expression.comparison.value.ComparableValue;

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
    public TruthValue apply(@Nonnull S subject) {
        T leftValue = this.left.get(subject);
        T rightValue = this.right.get(subject);

        if (leftValue == null || rightValue == null) {
            return TruthValue.UNKNOWN;
        }

        return operator.apply(leftValue, rightValue)
                ? TruthValue.TRUE
                : TruthValue.FALSE;

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