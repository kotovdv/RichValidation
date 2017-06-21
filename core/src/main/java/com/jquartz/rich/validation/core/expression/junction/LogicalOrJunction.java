package com.jquartz.rich.validation.core.expression.junction;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

public class LogicalOrJunction<T> implements ExpressionsJunction<T> {

    private List<ExpressionsJunction<T>> expressionChunks = new ArrayList<>();

    public LogicalOrJunction(Collection<ExpressionsJunction<T>> expressionChunks) {
        this.expressionChunks.addAll(expressionChunks != null ? expressionChunks : emptyList());
    }

    @Override
    public boolean verify(@Nonnull T instance) {
        boolean result = false;

        for (ExpressionsJunction<T> expression : expressionChunks) {
            result |= expression.verify(instance);
        }

        return result;
    }
}