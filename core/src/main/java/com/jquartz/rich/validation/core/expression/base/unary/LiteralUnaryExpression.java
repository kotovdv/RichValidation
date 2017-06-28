package com.jquartz.rich.validation.core.expression.base.unary;

import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.UnaryExpression;
import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;
import java.util.Collections;

public class LiteralUnaryExpression<T> extends UnaryExpression<T, LiteralPointer<?>> {

    public LiteralUnaryExpression(LiteralPointer<?> literalPointer, UnaryAction action) {
        super(literalPointer, action);
    }

    @Override
    protected boolean isOperandTrustworthy(LiteralPointer<?> operand, Trustworthiness trustworthiness) {
        return true;
    }

    @Override
    protected Object resolveOperand(LiteralPointer<?> operand, T subject) {
        return operand.resolve();
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return Collections.emptyList();
    }
}
