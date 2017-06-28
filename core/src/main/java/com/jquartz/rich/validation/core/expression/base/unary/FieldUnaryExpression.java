package com.jquartz.rich.validation.core.expression.base.unary;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.base.UnaryExpression;
import com.jquartz.rich.validation.core.expression.base.unary.action.UnaryAction;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.Collection;
import java.util.Collections;

public class FieldUnaryExpression<T> extends UnaryExpression<T, FieldPointer<?, T>> {

    public FieldUnaryExpression(FieldPointer<?, T> fieldPointer, UnaryAction action) {
        super(fieldPointer, action);
    }

    @Override
    public TruthValue apply(T subject) {
        return action.apply(operand.resolve(subject));
    }

    @Override
    public Collection<ClassField<T>> getAccomplices() {
        return Collections
                .singleton(new ClassField<>(operand.getSourceClass(), operand.getFieldName()));
    }


}
