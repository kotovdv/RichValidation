package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.expression.comparison.ComparisonExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogic<T> {

    private final List<ComparisonExpression> expressions;

    public VerificationLogic(List<ComparisonExpression<?,?>> expressions) {
        this.expressions = new ArrayList<>(expressions);
    }

    public VerificationResult apply(T instance) {
        for (ComparisonExpression expression : expressions) {
//            expression.apply(instance);
        }


        return null;
    }
}