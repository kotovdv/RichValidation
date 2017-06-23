package com.jquartz.rich.validation.core.api.dsl;

import javax.annotation.Nonnull;

public class RuleBuilder<T> {

    private final Class<T> targetClass;
    private RuleLogicBuilder<T> builder = new RuleLogicBuilder<>();

    private RuleBuilder(@Nonnull Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public static <T> RuleBuilder<T> ensureThat(@Nonnull Class<T> targetClass) {
        return new RuleBuilder<>(targetClass);
    }

    public TargetPartBuilder<T> field(@Nonnull String fieldName) {
        return new TargetPartBuilder<>(targetClass, fieldName, builder);
    }
}