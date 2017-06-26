package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;
import com.jquartz.rich.validation.core.pointer.Pointer;

public abstract class UnaryExpression<T, O extends Pointer> implements Expression<T> {

    protected final O operand;
    protected final UnaryAction action;

    protected UnaryExpression(O operand, UnaryAction unaryAction) {
        this.operand = operand;
        this.action = unaryAction;
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
