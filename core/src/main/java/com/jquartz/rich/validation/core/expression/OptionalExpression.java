package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.EmptyTrustworthiness;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;
import java.util.Collections;

import static com.jquartz.rich.validation.core.api.textual.Tokens.OPTIONAL;

/**
 * @author Dmitriy Kotov
 */
public class OptionalExpression<T> implements Expression<T> {
    @Override
    public TruthValue apply(T subject) {
        return apply(subject, EmptyTrustworthiness.INSTANCE);
    }

    @Override
    public TruthValue apply(T subject, Trustworthiness trustworthiness) {
        return TruthValue.TRUE;
    }

    @Override
    public String getTextualRepresentation() {
        return OPTIONAL.toString();
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}