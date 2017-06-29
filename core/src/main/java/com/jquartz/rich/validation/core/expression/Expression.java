package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

public interface Expression<T> {

    TruthValue apply(T subject, Trustworthiness trustworthiness);

    Collection<ClassField<?, T>> getAccomplices();

    String getTextualRepresentation();
}
