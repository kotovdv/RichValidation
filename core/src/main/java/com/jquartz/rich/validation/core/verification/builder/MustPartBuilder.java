package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.expression.Expression;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetBuilder;
    private final Expression<?> appliedExpression;

    public MustPartBuilder(TargetPartBuilder<T> targetBuilder,
                           Expression<?> appliedExpression) {
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
        return null;
    }
}
