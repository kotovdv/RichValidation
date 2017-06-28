package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;
import com.jquartz.rich.validation.core.pointer.Pointer;

public abstract class UnaryExpression<T, O extends Pointer> extends AbstractExpression<T> {

    protected final O operand;
    protected final UnaryAction action;

    protected UnaryExpression(O operand, UnaryAction action) {
        this.operand = operand;
        this.action = action;
    }

    @Override
    public String getTextualRepresentation() {
        return operand.getTextualRepresentation() + " "
                + action.getTextualRepresentation() + " ";
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}
