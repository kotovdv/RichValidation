package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

import static java.util.Collections.singleton;

public class FieldToLiteralBinaryExpression<T> extends BinaryExpression<T, FieldPointer<?, T>, LiteralPointer<?>> {

    public FieldToLiteralBinaryExpression(FieldPointer<?, T> leftOperand,
                                          BinaryAction action,
                                          LiteralPointer<?> rightOperand) {
        super(leftOperand, action, rightOperand);
    }

    @Override
    protected boolean areOperandsTrustworthy(Trustworthiness trustworthiness) {
        return trustworthiness.isTrustworthy(leftOperand.getTarget());
    }

    @Override
    protected Object resolveLeftOperand(FieldPointer<?, T> leftOperand, T source) {
        return leftOperand.resolve(source);
    }

    @Override
    protected Object resolveRightOperand(LiteralPointer<?> rightOperand, T source) {
        return rightOperand.resolve();
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return singleton(leftOperand.getTarget());
    }
}
