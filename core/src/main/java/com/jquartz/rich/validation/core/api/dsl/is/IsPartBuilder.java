package com.jquartz.rich.validation.core.api.dsl.is;

import com.jquartz.rich.validation.core.api.dsl.conjunction.ConjunctionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.base.binary.FieldToFieldBinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.FieldToLiteralBinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.equality.AreEqualAction;
import com.jquartz.rich.validation.core.expression.base.binary.action.equality.AreNotEqualAction;
import com.jquartz.rich.validation.core.expression.base.unary.FieldUnaryExpression;
import com.jquartz.rich.validation.core.expression.base.unary.action.nullability.IsNotNullAction;
import com.jquartz.rich.validation.core.expression.base.unary.action.nullability.IsNullAction;
import com.jquartz.rich.validation.core.expression.comparison.factory.ComparisonExpressionFactory;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.pointer.field.FieldPointerFactory;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointerFactory;

/**
 * @param <T>  Source class type
 * @param <IB> Is part builder type
 * @param <CB> Conjunction part builder type
 * @author Dmitriy Kotov
 */
public abstract class IsPartBuilder<T, IB extends IsPartBuilder<T, IB, CB>, CB extends ConjunctionPartBuilder<T, IB, CB>> {

    protected final RuleLogicBuilder<T> builder;
    private final Class<T> targetClass;
    private final FieldPointer<?, T> targetFieldPointer;
    private final FieldPointerFactory fieldPointerFactory = new FieldPointerFactory();
    private final LiteralPointerFactory literalPointerFactory = new LiteralPointerFactory();
    private final ComparisonExpressionFactory comparisonExpressionFactory = new ComparisonExpressionFactory();

    IsPartBuilder(Class<T> targetClass, String fieldName, RuleLogicBuilder<T> builder) {
        this.targetClass = targetClass;
        this.builder = builder;
        this.targetFieldPointer = fieldPointerFactory.create(targetClass, fieldName);
    }

    public <V> CB isGreaterThan(V value) {
        return is(ComparisonOperator.GREATER_THAN, literalPointerFactory.create(value));
    }

    public <V> CB isGreaterOrCoequalTo(V value) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, literalPointerFactory.create(value));
    }

    public <V> CB isLessThan(V value) {
        return is(ComparisonOperator.LESS_THAN, literalPointerFactory.create(value));
    }

    public <V> CB isLessOrCoequalTo(V value) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, literalPointerFactory.create(value));
    }

    public CB isEqualToField(String fieldName) {
        return addExpression(new FieldToFieldBinaryExpression<>(
                targetFieldPointer,
                new AreEqualAction(),
                fieldPointerFactory.create(targetClass, fieldName)));
    }

    public CB isNotEqualToField(String fieldName) {
        return addExpression(new FieldToFieldBinaryExpression<>(
                targetFieldPointer,
                new AreNotEqualAction(),
                fieldPointerFactory.create(targetClass, fieldName)));
    }

    public <V> CB isEqualTo(V value) {
        return addExpression(new FieldToLiteralBinaryExpression<>(
                this.targetFieldPointer,
                new AreEqualAction(),
                literalPointerFactory.create(value)));
    }

    public <V> CB isNotEqualTo(V value) {
        return addExpression(new FieldToLiteralBinaryExpression<>(
                this.targetFieldPointer,
                new AreNotEqualAction(),
                literalPointerFactory.create(value)));
    }

    public CB isGreaterThanField(String fieldName) {
        return is(ComparisonOperator.GREATER_THAN, fieldPointerFactory.create(targetClass, fieldName));
    }

    public CB isGreaterOrCoequalToField(String fieldName) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, fieldPointerFactory.create(targetClass, fieldName));
    }

    public CB isLessThanField(String fieldName) {
        return is(ComparisonOperator.LESS_THAN, fieldPointerFactory.create(targetClass, fieldName));
    }

    public CB isLessOrCoequalToField(String fieldName) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, fieldPointerFactory.create(targetClass, fieldName));
    }

    public CB isCoequalToField(String fieldName) {
        return is(ComparisonOperator.EQUAL_TO, fieldPointerFactory.create(targetClass, fieldName));
    }

    public CB isNotCoequalToField(String fieldName) {
        return is(ComparisonOperator.NOT_EQUAL_TO, fieldPointerFactory.create(targetClass, fieldName));
    }

    public <V> CB isCoequalTo(V value) {
        return is(ComparisonOperator.EQUAL_TO, literalPointerFactory.create(value));
    }

    public <V> CB isNotCoequalTo(V value) {
        return is(ComparisonOperator.NOT_EQUAL_TO, literalPointerFactory.create(value));
    }

    public CB isNull() {
        return addExpression(new FieldUnaryExpression<>(targetFieldPointer, new IsNullAction()));
    }

    public CB isNotNull() {
        return addExpression(new FieldUnaryExpression<>(targetFieldPointer, new IsNotNullAction()));
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    protected abstract CB createConjunctionBuilder();

    private CB is(ComparisonOperator operator, LiteralPointer<?> pointer) {
        return addExpression(comparisonExpressionFactory.create(targetFieldPointer, operator, pointer));
    }

    private CB is(ComparisonOperator operator, FieldPointer<?, T> pointer) {
        return addExpression(comparisonExpressionFactory.create(targetFieldPointer, operator, pointer));
    }

    private CB addExpression(Expression<T> expression) {
        CB conjunctionBuilder = createConjunctionBuilder();
        builder.appendExpression(expression);

        return conjunctionBuilder;
    }
}