package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;

public interface Expression<T> {

    TruthValue apply(T subject);

    String getTextualRepresentation();
}
