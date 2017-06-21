package com.jquartz.rich.validation.core.verification.builder.must;

import com.jquartz.rich.validation.core.verification.expression.junction.ExpressionsJunction;
import com.jquartz.rich.validation.core.verification.expression.junction.LogicalOrJunction;

import java.util.Deque;
import java.util.LinkedList;

import static java.util.stream.Collectors.toList;

public class LogicalOrJunctionBuilder<T> {
    private final Deque<LogicalAndJunctionBuilder<T>> partsJoinedByOr = new LinkedList<>();

    public LogicalOrJunctionBuilder() {
        this.partsJoinedByOr.push(new LogicalAndJunctionBuilder<>());
    }

    public LogicalAndJunctionBuilder<T> getLatestPart() {
        return partsJoinedByOr.peek();
    }

    public void startNewOrJunction() {
        partsJoinedByOr.push(new LogicalAndJunctionBuilder<>());
    }

    public Iterable<LogicalAndJunctionBuilder<T>> extract() {
        return partsJoinedByOr;
    }

    public ExpressionsJunction<T> build() {
        return new LogicalOrJunction<>(partsJoinedByOr.stream()
                .map(LogicalAndJunctionBuilder::build)
                .collect(toList())
        );
    }
}