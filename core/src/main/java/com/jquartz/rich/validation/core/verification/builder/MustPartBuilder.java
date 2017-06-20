package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.expression.Expression;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetBuilder;
    private final Expression<T> appliedExpression;

    public MustPartBuilder(TargetPartBuilder<T> targetBuilder,
                           Expression<T> appliedExpression) {
        this.targetBuilder = targetBuilder;
        this.appliedExpression = appliedExpression;
    }

    //TODO different types of must parts
    public TargetPartBuilder<T> and() {
        return targetBuilder;
    }

    public TargetPartBuilder<T> or() {
        targetBuilder.newOrPart();

        return targetBuilder;
    }

    public VerificationLogic<T> create() {
        return new VerificationLogic<>(targetBuilder.extractExpressions());
    }

    public Expression<T> getAppliedExpression() {
        return appliedExpression;
    }
}
