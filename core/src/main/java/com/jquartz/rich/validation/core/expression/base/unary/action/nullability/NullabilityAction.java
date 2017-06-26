package com.jquartz.rich.validation.core.expression.base.unary.action.nullability;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;

import javax.annotation.Nullable;

public abstract class NullabilityAction implements UnaryAction {

    @Override
    public <V> TruthValue apply(V value) {
        return applyNullabilityCheck(value)
                ? TruthValue.TRUE
                : TruthValue.FALSE;
    }

    protected abstract boolean applyNullabilityCheck(@Nullable Object value);
}