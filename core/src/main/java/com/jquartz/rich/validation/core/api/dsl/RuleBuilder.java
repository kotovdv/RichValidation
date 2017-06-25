package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.api.dsl.is.TargetIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

import javax.annotation.Nonnull;

public class RuleBuilder<T> {

    private final Class<T> targetClass;

    private RuleBuilder(@Nonnull Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public static <T> RuleBuilder<T> ensureThat(@Nonnull Class<T> targetClass) {
        return new RuleBuilder<>(targetClass);
    }

    public TargetIsPartBuilder<T> field(@Nonnull String fieldName) {
        RuleLogicBuilder<T> builder = new RuleLogicBuilder<>(targetClass, fieldName);

        return new TargetIsPartBuilder<>(builder);
    }
}