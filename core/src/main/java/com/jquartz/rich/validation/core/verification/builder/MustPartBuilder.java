package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.expression.Expression;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetBuilder;
    private final Expression<T> appliedExpression;
    private final VerificationLogicHolder<T> holder;

    public MustPartBuilder(TargetPartBuilder<T> targetBuilder,
                           Expression<T> appliedExpression,
                           VerificationLogicHolder<T> holder) {
        this.targetBuilder = targetBuilder;
        this.appliedExpression = appliedExpression;
        this.holder = holder;
    }

    public TargetPartBuilder<T> and() {
        return targetBuilder;
    }

    public TargetPartBuilder<T> or() {
        holder.startNewOrJunction();

        return targetBuilder;
    }

    public VerificationLogic<T> create() {
        return holder.createLogic();
    }

    public Expression<T> getAppliedExpression() {
        return appliedExpression;
    }
}
