package com.jquartz.rich.validation.core.verification.expression.junction;

import com.jquartz.rich.validation.core.verification.expression.Expression;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

public class LogicalAndJunction<T> implements ExpressionsJunction<T> {

    private final List<Expression<T>> expressions = new ArrayList<>();

    public LogicalAndJunction(Collection<Expression<T>> expressions) {
        this.expressions.addAll(expressions != null ? expressions : emptyList());
    }

    @Override
    public boolean verify(@Nonnull T instance) {
        boolean result = true;

        for (Expression<T> expression : expressions) {
            result &= expression.apply(instance);
        }

        return result;
    }
}