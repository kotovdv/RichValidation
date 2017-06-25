package com.jquartz.rich.validation.core.expression.common;

import com.jquartz.rich.validation.core.expression.value.Value;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.jquartz.rich.validation.core.api.textual.Tokens.IS_NOT_EQUAL_TO;

/**
 * @author Dmitriy Kotov
 */
public class AreNotEqualExpression<T> extends EqualityExpression<T> {

    public AreNotEqualExpression(Value<?, T> left, Value<?, T> right) {
        super(left, right);
    }

    @Override
    protected boolean applyEqualityCheck(@Nullable Object left, @Nullable Object right) {
        return !Objects.equals(left, right);
    }

    @Override
    public String getTextualRepresentation() {
        return left.getTextualRepresentation() + " " + IS_NOT_EQUAL_TO + " " + right.getTextualRepresentation();
    }
}