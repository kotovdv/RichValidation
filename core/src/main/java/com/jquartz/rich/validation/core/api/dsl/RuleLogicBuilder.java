package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.junction.builder.LogicalOrJunctionBuilder;

class RuleLogicBuilder<T> {

    private LogicalOrJunctionBuilder<T> orJunction = new LogicalOrJunctionBuilder<>();

    public void startNewOrJunction() {
        orJunction.startNewOrJunction();
    }

    public void appendExpression(Expression<T> expression) {
        orJunction.getLatestPart().addExpression(expression);
    }

    public Rule<T> build() {
        return new Rule<>(orJunction.build());
    }
}