package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.builder.must.LogicalOrJunctionBuilder;
import com.jquartz.rich.validation.core.verification.expression.Expression;

public class VerificationLogicHolder<T> {

    private LogicalOrJunctionBuilder<T> orJunction = new LogicalOrJunctionBuilder<>();

    public void startNewOrJunction() {
        orJunction.startNewOrJunction();
    }

    public void appendExpression(Expression<T> expression) {
        orJunction.getLatestPart().addExpression(expression);
    }

    public VerificationLogic<T> createLogic() {
        return new VerificationLogic<>(orJunction.build());
    }
}