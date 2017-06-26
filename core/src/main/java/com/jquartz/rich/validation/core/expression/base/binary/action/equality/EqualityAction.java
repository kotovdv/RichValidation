package com.jquartz.rich.validation.core.expression.base.binary.action.equality;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;

import javax.annotation.Nullable;

/**
 * @author Dmitriy Kotov
 */
public abstract class EqualityAction implements BinaryAction {

    @Override
    public <V> TruthValue apply(V leftOperand, V rightOperand) {
        return applyEqualityCheck(leftOperand, rightOperand)
                ? TruthValue.TRUE
                : TruthValue.FALSE;
    }

    protected abstract boolean applyEqualityCheck(@Nullable Object left, @Nullable Object right);

    @Override
    public String toString() {
        return getTextualRepresentation();
    }
}