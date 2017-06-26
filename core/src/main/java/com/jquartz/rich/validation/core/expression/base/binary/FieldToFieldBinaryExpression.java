package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;

public class FieldToFieldBinaryExpression<T> extends BinaryExpression<T, FieldPointer<?, T>, FieldPointer<?, T>> {

    public FieldToFieldBinaryExpression(FieldPointer<?, T> leftOperand,
                                        BinaryAction action,
                                        FieldPointer<?, T> rightOperand) {
        super(leftOperand, action, rightOperand);
    }

    @Override
    public TruthValue apply(T subject) {
        return action.apply(
                leftOperand.resolve(subject),
                rightOperand.resolve(subject)
        );
    }
}
