package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.comparison.factory.ComparisonExpressionLogicFactory;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.pointer.FieldPointer;
import com.jquartz.rich.validation.core.pointer.FieldPointerFactory;
import com.jquartz.rich.validation.core.pointer.LiteralPointer;

public class TargetPartBuilder<T> {

    private final Class<T> targetClass;
    private final FieldPointer<?, T> targetFieldPointer;
    private final ValidationLogicBuilder<T> holder;
    private final FieldPointerFactory pointerFactory = new FieldPointerFactory();
    private final ComparisonExpressionLogicFactory logicFactory = new ComparisonExpressionLogicFactory();

    TargetPartBuilder(Class<T> targetClass, String fieldName, ValidationLogicBuilder<T> holder) {
        this.targetClass = targetClass;
        this.holder = holder;
        this.targetFieldPointer = pointerFactory.createPointer(targetClass, fieldName);
    }

    public MustPartBuilder<T> isGreaterThanField(String fieldName) {
        return is(ComparisonOperator.GREATER_THAN, pointerFactory.createPointer(targetClass, fieldName));
    }

    public MustPartBuilder<T> isGreaterOrEqualToField(String fieldName) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public MustPartBuilder<T> isLessThanField(String fieldName) {
        return is(ComparisonOperator.LESS_THAN, pointerFactory.createPointer(targetClass, fieldName));
    }

    public MustPartBuilder<T> isLessOrEqualToField(String fieldName) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public MustPartBuilder<T> isEqualToField(String fieldName) {
        return is(ComparisonOperator.EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public MustPartBuilder<T> isNotEqualToField(String fieldName) {
        return is(ComparisonOperator.NOT_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public <V> MustPartBuilder<T> isGreaterThan(V value) {
        return is(ComparisonOperator.GREATER_THAN, new LiteralPointer<>(value));
    }

    public <V> MustPartBuilder<T> isGreaterOrEqualTo(V value) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> MustPartBuilder<T> isLessThan(V value) {
        return is(ComparisonOperator.LESS_THAN, new LiteralPointer<>(value));
    }

    public <V> MustPartBuilder<T> isLessOrEqualTo(V value) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> MustPartBuilder<T> isEqualTo(V value) {
        return is(ComparisonOperator.EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> MustPartBuilder<T> isNotEqualTo(V value) {
        return is(ComparisonOperator.NOT_EQUAL_TO, new LiteralPointer<>(value));
    }

    private MustPartBuilder<T> is(ComparisonOperator operator, LiteralPointer<?> pointer) {
        return addExpression(logicFactory.create(targetFieldPointer, operator, pointer));
    }

    private MustPartBuilder<T> is(ComparisonOperator operator, FieldPointer<?, T> pointer) {
        return addExpression(logicFactory.create(targetFieldPointer, operator, pointer));
    }

    private MustPartBuilder<T> addExpression(Expression<T> expression) {
        MustPartBuilder<T> newMustPart = new MustPartBuilder<>(this, holder);
        holder.appendExpression(expression);

        return newMustPart;
    }
}
