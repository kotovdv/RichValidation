package com.jquartz.rich.validation.core.verification.builder;

import javax.annotation.Nonnull;

public class VerificationLogicBuilder<T> {

    private final Class<T> targetClass;
    private VerificationLogicHolder<T> verificationLogicHolder = new VerificationLogicHolder<>();

    public static <T> VerificationLogicBuilder<T> ensureThat(@Nonnull Class<T> targetClass) {
        return new VerificationLogicBuilder<>(targetClass);
    }

    private VerificationLogicBuilder(@Nonnull Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public TargetPartBuilder<T> field(@Nonnull String fieldName) {
        return new TargetPartBuilder<>(targetClass, fieldName, verificationLogicHolder);
    }
}
