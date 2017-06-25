package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.junction.LogicalOrJunction;

import java.util.Deque;
import java.util.LinkedList;

import static java.util.stream.Collectors.toList;

public class LogicalOrJunctionBuilder<T> implements BuildableExpression<T> {
    private final Deque<LogicalAndJunctionBuilder<T>> partsJoinedByOr = new LinkedList<>();

    public void addExpression(Expression<T> expression) {
        LogicalAndJunctionBuilder<T> latestPart = getLatestPart();
        latestPart.addExpression(expression);
    }

    public void startNewOrJunction() {
        partsJoinedByOr.push(new LogicalAndJunctionBuilder<>());
    }

    @Override
    public Expression<T> build() {
        if (partsJoinedByOr.isEmpty()) {
            throw new UnableToConstructEmptyJunctionException();
        }

        return new LogicalOrJunction<>(partsJoinedByOr.stream()
                .map(LogicalAndJunctionBuilder::build)
                .collect(toList())
        );
    }

    @Override
    public void clear() {
        this.partsJoinedByOr.forEach(LogicalAndJunctionBuilder::clear);
        this.partsJoinedByOr.clear();
    }

    @Override
    public boolean isEmpty() {
        return partsJoinedByOr.isEmpty();
    }


    private LogicalAndJunctionBuilder<T> getLatestPart() {
        if (partsJoinedByOr.isEmpty()) {
            this.partsJoinedByOr.push(new LogicalAndJunctionBuilder<>());
        }

        return partsJoinedByOr.peek();
    }

}