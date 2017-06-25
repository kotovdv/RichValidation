package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;

/**
 * @author Dmitriy Kotov
 */
public abstract class EqualityExpression<T> implements Expression<T> {

    protected final Value<?, T> left;
    protected final Value<?, T> right;

    EqualityExpression(Value<?, T> left, Value<?, T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public TruthValue apply(T subject) {
        Object left = this.left.get(subject);
        Object right = this.right.get(subject);

        return applyEqualityCheck(left, right)
                ? TruthValue.TRUE
                : TruthValue.FALSE;
    }

    protected abstract boolean applyEqualityCheck(@Nullable Object left, @Nullable Object right);

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}