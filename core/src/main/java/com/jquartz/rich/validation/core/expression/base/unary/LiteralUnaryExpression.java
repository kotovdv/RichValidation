package com.jquartz.rich.validation.core.expression.base.unary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
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
    public TruthValue apply(T subject) {
        return action.apply(operand.resolve());
    }

    @Override
    public Collection<ClassField<T>> getAccomplices() {
        return Collections.emptyList();
    }
}
