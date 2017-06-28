package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

import static java.util.Arrays.asList;

public class FieldToFieldBinaryExpression<T> extends BinaryExpression<T, FieldPointer<?, T>, FieldPointer<?, T>> {

    public FieldToFieldBinaryExpression(FieldPointer<?, T> leftOperand,
                                        BinaryAction action,
                                        FieldPointer<?, T> rightOperand) {
        super(leftOperand, action, rightOperand);
    }

    @Override
    protected boolean areOperandsTrustworthy(Trustworthiness trustworthiness) {
        return trustworthiness.isTrustworthy(leftOperand.getTarget()) && trustworthiness.isTrustworthy(rightOperand.getTarget());
    }

    @Override
    protected Object resolveLeftOperand(FieldPointer<?, T> leftOperand, T source) {
        return leftOperand.resolve(source);
    }

    @Override
    protected Object resolveRightOperand(FieldPointer<?, T> rightOperand, T source) {
        return rightOperand.resolve(source);
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return asList(leftOperand.getTarget(), rightOperand.getTarget());
    }
}
