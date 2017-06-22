package com.jquartz.rich.validation.core.evaluation;

import javax.annotation.Nonnull;

public enum TruthValue {
    TRUE,
    FALSE,
    UNKNOWN;

    public TruthValue and(@Nonnull TruthValue anotherValue) {
        return TruthValueBinaryOperator.AND.apply(this, anotherValue);
    }

    public TruthValue or(@Nonnull TruthValue anotherValue) {
        return TruthValueBinaryOperator.OR.apply(this, anotherValue);
    }
}