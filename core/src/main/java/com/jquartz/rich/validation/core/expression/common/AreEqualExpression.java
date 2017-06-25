package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author Dmitriy Kotov
 */
public class AreEqualExpression<T> extends EqualityExpression<T> {

    public AreEqualExpression(Value<?, T> left, Value<?, T> right) {
        super(left, right);
    }

    @Override
    protected boolean applyEqualityCheck(@Nullable Object left, @Nullable Object right) {
        return Objects.equals(left, right);
    }
}