package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.expression.Expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogic<T> {

    private final List<Expression<T>> expressions = new ArrayList<>();

    public VerificationLogic(Collection<Expression<T>> expressions) {
        this.expressions.addAll(expressions != null ? expressions : emptyList());
    }

    public boolean verify(T instance) {
        boolean result = true;

        for (Expression<T> expression : expressions) {
            result &= expression.apply(instance);
        }

        return result;
    }
}