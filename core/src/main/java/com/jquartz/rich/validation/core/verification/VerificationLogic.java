package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.expression.LogicExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogic {

    private final List<LogicExpression> expressions;

    public VerificationLogic(List<LogicExpression<?>> expressions) {
        this.expressions = new ArrayList<>(expressions);
    }

    public <T> VerificationResult apply(T instance) {
        for (LogicExpression expression : expressions) {
//            expression.apply(instance);
        }


        return null;
    }
}