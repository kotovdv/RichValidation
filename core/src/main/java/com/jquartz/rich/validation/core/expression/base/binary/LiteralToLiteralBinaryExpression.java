package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;
import java.util.Collections;

public class LiteralToLiteralBinaryExpression extends BinaryExpression<Object, LiteralPointer<?>, LiteralPointer<?>> {

    public LiteralToLiteralBinaryExpression(LiteralPointer<?> leftOperand,
                                            BinaryAction action,
                                            LiteralPointer<?> rightOperand) {
        super(leftOperand, action, rightOperand);
    }

    @Override
    protected boolean areOperandsTrustworthy(Trustworthiness trustworthiness) {
        return true;
    }

    @Override
    protected Object resolveLeftOperand(LiteralPointer<?> leftOperand, Object source) {
        return leftOperand.resolve();
    }

    @Override
    protected Object resolveRightOperand(LiteralPointer<?> rightOperand, Object source) {
        return rightOperand.resolve();
    }

    @Override
    public Collection<ClassField<?, Object>> getAccomplices() {
        return Collections.emptyList();
    }
}
