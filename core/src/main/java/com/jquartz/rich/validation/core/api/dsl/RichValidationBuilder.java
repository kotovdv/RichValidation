package com.jquartz.rich.validation.core.api.dsl;

import javax.annotation.Nonnull;

public class RichValidationBuilder<T> {

    private final Class<T> targetClass;
    private ValidationLogicBuilder<T> builder = new ValidationLogicBuilder<>();

    private RichValidationBuilder(@Nonnull Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public static <T> RichValidationBuilder<T> ensureThat(@Nonnull Class<T> targetClass) {
        return new RichValidationBuilder<>(targetClass);
    }

    public TargetPartBuilder<T> field(@Nonnull String fieldName) {
        return new TargetPartBuilder<>(targetClass, fieldName, builder);
    }
}