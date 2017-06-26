package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.Pointer;

public abstract class BinaryExpression<T, L extends Pointer, R extends Pointer> implements Expression<T> {

    protected final L leftOperand;
    protected final BinaryAction action;
    protected final R rightOperand;

    protected BinaryExpression(L leftOperand,
                               BinaryAction action,
                               R rightOperand) {
        this.leftOperand = leftOperand;
        this.action = action;
        this.rightOperand = rightOperand;
    }

    @Override
    public String getTextualRepresentation() {
        return leftOperand.getTextualRepresentation() + " " +
                action.getTextualRepresentation() + " " +
                rightOperand.getTextualRepresentation() + " ";
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
