package com.jquartz.rich.validation.core.verification.builder.must;

import com.jquartz.rich.validation.core.verification.builder.MustPartBuilder;

import java.util.ArrayList;
import java.util.List;

public class PartsJoinedByAndBuilder {

    private List<MustPartBuilder<?>> expressions = new ArrayList<>();

    public void addExpression(MustPartBuilder<?> mustPartBuilder) {
        this.expressions.add(mustPartBuilder);
    }
}
