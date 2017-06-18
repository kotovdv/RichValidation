package com.jquartz.rich.validation.core.verification.expression;

import com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.value.Value;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class LogicExpression<T extends Comparable<T>> {

    private final Value<T> leftValue;
    private final ComparisonOperator comparisonOperator;
    private final Value<T> rightValue;

    public LogicExpression(Value<T> leftValue,
                           ComparisonOperator comparisonOperator,
                           Value<T> rightValue) {
        this.leftValue = leftValue;
        this.comparisonOperator = comparisonOperator;
        this.rightValue = rightValue;
    }

    public boolean apply(@Nonnull T source) {
        return comparisonOperator.apply(
                this.leftValue.get(source),
                this.rightValue.get(source)
        );
    }

    @Override
    public String toString() {
        return "LogicExpression{" +
                "leftValue=" + leftValue +
                ", comparisonOperator=" + comparisonOperator +
                ", rightValue=" + rightValue +
                '}';
    }
}


//Options
// 1. Create four types of expressions in which each one of them represents
// Field vs Literal
// Literal vs Field
// Literal vs Literal
// Field vs Field


//2. Stick with the current one, where we have two inheritors of Value interface - one is literal/one is field
// In case of field - it extracts value from source. In case of literal - it just returns literal value

//3. Create separate list of FieldValue, create method resolve which accepts source as an input argument
// when all fields are resolved - use our expression

