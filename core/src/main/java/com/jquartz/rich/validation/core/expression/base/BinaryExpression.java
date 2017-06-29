package com.jquartz.rich.validation.core.expression.base;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.Pointer;

public abstract class BinaryExpression<T, L extends Pointer, R extends Pointer> extends AbstractExpression<T> {

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
    public final TruthValue apply(T subject, Trustworthiness trustworthiness) {
        return areOperandsTrustworthy(trustworthiness)
                ? action.apply(resolveLeftOperand(leftOperand, subject), resolveRightOperand(rightOperand, subject))
                : TruthValue.UNKNOWN;
    }

    protected abstract boolean areOperandsTrustworthy(Trustworthiness trustworthiness);

    protected abstract Object resolveLeftOperand(L leftOperand, T source);

    protected abstract Object resolveRightOperand(R rightOperand, T source);
}
