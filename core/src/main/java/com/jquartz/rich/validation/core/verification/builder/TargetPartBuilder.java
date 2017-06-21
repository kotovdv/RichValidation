package com.jquartz.rich.validation.core.verification.builder;

import com.jquartz.rich.validation.core.api.FieldPointer;
import com.jquartz.rich.validation.core.api.LiteralPointer;
import com.jquartz.rich.validation.core.verification.builder.must.PartsJoinedByAndBuilder;
import com.jquartz.rich.validation.core.verification.expression.Expression;
import com.jquartz.rich.validation.core.verification.expression.comparison.factory.ComparisonExpressionLogicFactory;
import com.jquartz.rich.validation.core.verification.expression.comparison.operator.ComparisonOperator;

import javax.annotation.Nonnull;
import java.util.*;

public class TargetPartBuilder<T> {

    private final Class<T> targetClass;
    private final FieldPointer<?, T> targetFieldPointer;
    private final Deque<PartsJoinedByAndBuilder<T>> partsJoinedByOr = new LinkedList<>();
    private final FieldPointerFactory pointerFactory = new FieldPointerFactory();

    private final ComparisonExpressionLogicFactory logicFactory = new ComparisonExpressionLogicFactory();

    public TargetPartBuilder(Class<T> targetClass, @Nonnull FieldPointer<?, T> targetPointer) {
        this.targetClass = targetClass;
        this.targetFieldPointer = targetPointer;
        this.partsJoinedByOr.add(new PartsJoinedByAndBuilder<>());
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
        PartsJoinedByAndBuilder<T> lastPart = this.partsJoinedByOr.peek();

        MustPartBuilder<T> newMustPart = new MustPartBuilder<>(this, expression);
        lastPart.addExpression(newMustPart);

        return newMustPart;
    }

    void newOrPart() {
        this.partsJoinedByOr.push(new PartsJoinedByAndBuilder<>());
    }

    Collection<Expression<T>> extractExpressions() {
        List<Expression<T>> expressions = new ArrayList<>();
        for (PartsJoinedByAndBuilder<T> partsJoinedByAndBuilder : partsJoinedByOr) {
            List<MustPartBuilder<T>> mustParts = partsJoinedByAndBuilder.getMustParts();

            for (MustPartBuilder<T> mustPart : mustParts) {
                expressions.add(mustPart.getAppliedExpression());
            }
        }

        return expressions;
    }
}
