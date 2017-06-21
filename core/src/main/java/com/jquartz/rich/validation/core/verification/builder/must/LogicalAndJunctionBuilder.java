package com.jquartz.rich.validation.core.verification.builder.must;

import com.jquartz.rich.validation.core.verification.builder.MustPartBuilder;
import com.jquartz.rich.validation.core.verification.expression.junction.ExpressionsJunction;
import com.jquartz.rich.validation.core.verification.expression.junction.LogicalAndJunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogicalAndJunctionBuilder<T> {

    private List<MustPartBuilder<T>> mustParts = new ArrayList<>();

    public void addExpression(MustPartBuilder<T> mustPartBuilder) {
        this.mustParts.add(mustPartBuilder);
    }

    public ExpressionsJunction<T> build() {
        return new LogicalAndJunction<>(mustParts.stream()
                .map(MustPartBuilder::getAppliedExpression)
                .collect(Collectors.toList())
        );
    }
}
