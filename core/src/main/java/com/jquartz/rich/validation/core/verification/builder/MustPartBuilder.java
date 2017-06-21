package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetBuilder;
    private final VerificationLogicHolder<T> holder;

    public MustPartBuilder(TargetPartBuilder<T> targetBuilder,
                           VerificationLogicHolder<T> holder) {
        this.targetBuilder = targetBuilder;
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
}
