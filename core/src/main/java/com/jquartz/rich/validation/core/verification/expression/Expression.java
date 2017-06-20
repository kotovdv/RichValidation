package com.jquartz.rich.validation.core.verification.expression;

import javax.annotation.Nonnull;

public interface Expression<T> {

    boolean apply(@Nonnull T subject);
}
