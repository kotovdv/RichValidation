package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.junction.ExpressionsJunction;
import com.jquartz.rich.validation.core.expression.junction.LogicalAndJunction;

import java.util.ArrayList;
import java.util.List;

public class LogicalAndJunctionBuilder<T> {

    private List<Expression<T>> expressions = new ArrayList<>();

    public void addExpression(Expression<T> mustPartBuilder) {
        this.expressions.add(mustPartBuilder);
    }

    public ExpressionsJunction<T> build() {
        return new LogicalAndJunction<>(new ArrayList<>(expressions));
    }
}
