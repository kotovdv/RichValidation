package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.Rule;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetPart;
    private final RuleLogicBuilder<T> logicBuilder;

    MustPartBuilder(TargetPartBuilder<T> targetPart,
                    RuleLogicBuilder<T> logicBuilder) {
        this.targetPart = targetPart;
        this.logicBuilder = logicBuilder;
    }

    public TargetPartBuilder<T> and() {
        return targetPart;
    }

    public TargetPartBuilder<T> or() {
        logicBuilder.startNewOrJunction();

        return targetPart;
    }

    public Rule<T> build() {
        return logicBuilder.build();
    }
}