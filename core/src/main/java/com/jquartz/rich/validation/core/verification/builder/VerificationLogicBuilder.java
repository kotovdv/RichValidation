package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.api.FieldPointer;

import javax.annotation.Nonnull;

public class VerificationLogicBuilder<T> {

    private final Class<T> targetClass;
    private final FieldPointerFactory pointerFactory = new FieldPointerFactory();

    public static <T> VerificationLogicBuilder<T> ensureThat(@Nonnull Class<T> targetClass) {
        return new VerificationLogicBuilder<>(targetClass);
    }

    private VerificationLogicBuilder(@Nonnull Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public TargetPartBuilder<T> field(@Nonnull String fieldName) {
        FieldPointer<?, T> targetPointer = pointerFactory.createPointer(targetClass, fieldName);

        return new TargetPartBuilder<>(targetClass, targetPointer);
    }
}
