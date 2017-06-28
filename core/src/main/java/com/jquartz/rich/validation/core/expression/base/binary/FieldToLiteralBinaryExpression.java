package com.jquartz.rich.validation.core.expression.base.binary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.expression.base.BinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.BinaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;

import static java.util.Collections.singleton;

public class FieldToLiteralBinaryExpression<T> extends BinaryExpression<T, FieldPointer<?, T>, LiteralPointer<?>> {

    public FieldToLiteralBinaryExpression(FieldPointer<?, T> leftOperand,
                                          BinaryAction action,
                                          LiteralPointer<?> rightOperand) {
        super(leftOperand, action, rightOperand);
    }


    @Override
    public TruthValue apply(T subject, Trustworthiness trustworthiness) {
        return action.apply(
                leftOperand.resolve(subject),
                rightOperand.resolve()
        );
    }

    @Override
    public Collection<ClassField<?, T>> getAccomplices() {
        return singleton(leftOperand.getTarget());
    }
}
