package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.ValidationLogic;

public class MustPartBuilder<T> {

    private final TargetPartBuilder<T> targetPart;
    private final ValidationLogicBuilder<T> logicBuilder;

    MustPartBuilder(TargetPartBuilder<T> targetPart,
                    ValidationLogicBuilder<T> logicBuilder) {
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

    public ValidationLogic<T> build() {
        return logicBuilder.build();
    }
}