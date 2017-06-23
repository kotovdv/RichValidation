package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;

public class IsNotNullExpression<T> extends NullabilityExpression<T> {

    public IsNotNullExpression(Value<?, T> value) {
        super(value);
    }

    @Override
    protected boolean applyNullabilityCheck(@Nullable Object value) {
        return value != null;
    }
}