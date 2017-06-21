package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.builder.must.LogicalOrJunctionBuilder;

public class VerificationLogicHolder<T> {

    private LogicalOrJunctionBuilder<T> orJunction = new LogicalOrJunctionBuilder<>();

    public void startNewOrJunction() {
        orJunction.startNewOrJunction();
    }

    public void appendLogic(MustPartBuilder<T> mustPartBuilder) {
        orJunction.getLatestPart().addExpression(mustPartBuilder);
    }

    public VerificationLogic<T> createLogic() {
        return new VerificationLogic<>(orJunction.build());
    }
}