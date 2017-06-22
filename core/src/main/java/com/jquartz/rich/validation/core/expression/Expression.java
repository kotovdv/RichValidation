package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;

import javax.annotation.Nonnull;

public interface Expression<T> {

    TruthValue apply(@Nonnull T subject);
}
