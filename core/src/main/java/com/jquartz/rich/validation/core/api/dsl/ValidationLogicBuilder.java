package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.ValidationLogic;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalOrJunctionBuilder;

class ValidationLogicBuilder<T> {

    private LogicalOrJunctionBuilder<T> orJunction = new LogicalOrJunctionBuilder<>();

    public void startNewOrJunction() {
        orJunction.startNewOrJunction();
    }

    public void appendExpression(Expression<T> expression) {
        orJunction.getLatestPart().addExpression(expression);
    }

    public ValidationLogic<T> build() {
        return new ValidationLogic<>(orJunction.build());
    }
}