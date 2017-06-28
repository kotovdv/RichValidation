package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
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
    public TruthValue apply(T subject, Trustworthiness trustworthiness) {
        boolean leftIsTrustworthy = trustworthiness.isTrustworthy(leftOperand.getTarget());
        boolean rightIsTrustworthy = trustworthiness.isTrustworthy(rightOperand.getTarget());

        return leftIsTrustworthy && rightIsTrustworthy
                ? action.apply(leftOperand.resolve(subject), rightOperand.resolve(subject))
                : TruthValue.UNKNOWN;
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return asList(leftOperand.getTarget(), rightOperand.getTarget());
    }
}
