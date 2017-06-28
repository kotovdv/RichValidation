package com.jquartz.rich.validation.core.expression.base.unary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.EmptyTrustworthiness;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.UnaryExpression;
import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

import static java.util.Collections.singleton;

public class FieldUnaryExpression<T> extends UnaryExpression<T, FieldPointer<?, T>> {

    public FieldUnaryExpression(FieldPointer<?, T> fieldPointer, UnaryAction action) {
        super(fieldPointer, action);
    }

    @Override
    public TruthValue apply(T subject) {
        return this.apply(subject, EmptyTrustworthiness.INSTANCE);
    }

    @Override
    public TruthValue apply(T subject, Trustworthiness trustworthiness) {
        ClassField<?, T> target = operand.getTarget();

        TruthValue validationResult = trustworthiness.isTrustworthy(target)
                ? action.apply(operand.resolve(subject))
                : TruthValue.UNKNOWN;

        trustworthiness.associateValidationResult(target, validationResult);

        return validationResult;
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return singleton(operand.getTarget());
    }


}
