package com.jquartz.rich.validation.core.expression.base.unary.action.nullability;

import com.jquartz.rich.validation.core.api.textual.Tokens;

import javax.annotation.Nullable;

public class IsNotNullAction extends NullabilityAction {

    @Override
    protected boolean applyNullabilityCheck(@Nullable Object value) {
        return value != null;
    }

    @Override
    public String getTextualRepresentation() {
        return Tokens.IS_NOT_NULL.toString();
    }
}