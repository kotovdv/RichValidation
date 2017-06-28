package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

public interface Expression<T> {

    TruthValue apply(T subject);

    String getTextualRepresentation();

    Collection<ClassField<T>> getAccomplices();
}
