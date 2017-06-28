package com.jquartz.rich.validation.core.expression.base.unary;

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
    protected boolean isOperandTrustworthy(FieldPointer<?, T> operand, Trustworthiness trustworthiness) {
        return trustworthiness.isTrustworthy(operand.getTarget());
    }

    @Override
    protected Object resolveOperand(FieldPointer<?, T> operand, T subject) {
        return operand.resolve(subject);
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return singleton(operand.getTarget());
    }
}
