package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;

import static com.jquartz.rich.validation.core.api.textual.Tokens.IS_NOT_NULL;

public class IsNotNullExpression<T> extends NullabilityExpression<T> {

    public IsNotNullExpression(Value<?, T> value) {
        super(value);
    }

    @Override
    protected boolean applyNullabilityCheck(@Nullable Object value) {
        return value != null;
    }

    @Override
    public String getTextualRepresentation() {
        return value.getTextualRepresentation() + " " + IS_NOT_NULL;
    }
}