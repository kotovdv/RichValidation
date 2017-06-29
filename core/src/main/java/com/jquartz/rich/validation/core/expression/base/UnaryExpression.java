package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
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
    public final TruthValue apply(T subject, Trustworthiness trustworthiness) {
        return isOperandTrustworthy(operand, trustworthiness)
                ? action.apply(resolveOperand(operand, subject))
                : TruthValue.UNKNOWN;
    }

    protected abstract boolean isOperandTrustworthy(O operand, Trustworthiness trustworthiness);

    protected abstract Object resolveOperand(O operand, T subject);

    @Override
    public String getTextualRepresentation() {
        return operand.getTextualRepresentation() + " "
                + action.getTextualRepresentation() + " ";
    }
}
