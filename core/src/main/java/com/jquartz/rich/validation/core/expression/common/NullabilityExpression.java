package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;

public abstract class NullabilityExpression<T> implements Expression<T> {

    private final Value<?, T> value;

    NullabilityExpression(Value<?, T> value) {
        this.value = value;
    }

    @Override
    public TruthValue apply(T source) {
        return applyNullabilityCheck(value.get(source))
                ? TruthValue.TRUE
                : TruthValue.FALSE;
    }

    protected abstract boolean applyNullabilityCheck(@Nullable Object value);
}