package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;

public class LiteralToLiteralBinaryExpression extends BinaryExpression<Object, LiteralPointer<?>, LiteralPointer<?>> {

    public LiteralToLiteralBinaryExpression(LiteralPointer<?> leftOperand,
                                            BinaryAction action,
                                            LiteralPointer<?> rightOperand) {
        super(leftOperand, action, rightOperand);
    }

    @Override
    public TruthValue apply(Object subject) {
        return action.apply(
                leftOperand.resolve(),
                rightOperand.resolve()
        );
    }
}
