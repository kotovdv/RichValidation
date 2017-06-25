package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.common.OptionalExpression;
import com.jquartz.rich.validation.core.expression.conditional.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.junction.LogicalElseJunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Kotov
 */
public class LogicalElseJunctionBuilder<T> implements BuildableExpression<T> {
    private final List<ConditionalExpression<T>> expressions = new ArrayList<>();
    private Expression<T> otherwise = new OptionalExpression<>();

    public void add(ConditionalExpression<T> expression) {
        this.expressions.add(expression);
    }

    public void setOtherwise(Expression<T> otherwise) {
        this.otherwise = otherwise;
    }

    @Override
    public Expression<T> build() {
        return new LogicalElseJunction<>(expressions, otherwise);
    }

    @Override
    public void clear() {
        this.expressions.clear();
        this.otherwise = null;
    }

    @Override
    public boolean isEmpty() {
        return expressions.isEmpty();
    }
}
