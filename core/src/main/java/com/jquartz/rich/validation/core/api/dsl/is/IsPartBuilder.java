package com.jquartz.rich.validation.core.api.dsl.is;

import com.jquartz.rich.validation.core.api.dsl.conjunction.ConjunctionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.common.IsNotNullExpression;
import com.jquartz.rich.validation.core.expression.common.IsNullExpression;
import com.jquartz.rich.validation.core.expression.comparison.factory.ComparisonExpressionFactory;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.expression.value.FieldValue;
import com.jquartz.rich.validation.core.pointer.FieldPointer;
import com.jquartz.rich.validation.core.pointer.FieldPointerFactory;
import com.jquartz.rich.validation.core.pointer.LiteralPointer;

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
    private final FieldPointerFactory pointerFactory = new FieldPointerFactory();
    private final ComparisonExpressionFactory comparisonExpressionFactory = new ComparisonExpressionFactory();

    IsPartBuilder(Class<T> targetClass, String fieldName, RuleLogicBuilder<T> builder) {
        this.targetClass = targetClass;
        this.builder = builder;
        this.targetFieldPointer = pointerFactory.createPointer(targetClass, fieldName);
    }

    public <V> CB isGreaterThan(V value) {
        return is(ComparisonOperator.GREATER_THAN, new LiteralPointer<>(value));
    }

    public <V> CB isGreaterOrEqualTo(V value) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> CB isLessThan(V value) {
        return is(ComparisonOperator.LESS_THAN, new LiteralPointer<>(value));
    }

    public <V> CB isLessOrEqualTo(V value) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> CB isEqualTo(V value) {
        return is(ComparisonOperator.EQUAL_TO, new LiteralPointer<>(value));
    }

    public <V> CB isNotEqualTo(V value) {
        return is(ComparisonOperator.NOT_EQUAL_TO, new LiteralPointer<>(value));
    }

    public CB isGreaterThanField(String fieldName) {
        return is(ComparisonOperator.GREATER_THAN, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isGreaterOrEqualToField(String fieldName) {
        return is(ComparisonOperator.GREATER_OR_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isLessThanField(String fieldName) {
        return is(ComparisonOperator.LESS_THAN, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isLessOrEqualToField(String fieldName) {
        return is(ComparisonOperator.LESS_OR_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isEqualToField(String fieldName) {
        return is(ComparisonOperator.EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isNotEqualToField(String fieldName) {
        return is(ComparisonOperator.NOT_EQUAL_TO, pointerFactory.createPointer(targetClass, fieldName));
    }

    public CB isNull() {
        return addExpression(new IsNullExpression<>(new FieldValue<>(targetFieldPointer)));
    }

    public CB isNotNull() {
        return addExpression(new IsNotNullExpression<>(new FieldValue<>(targetFieldPointer)));
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    private CB is(ComparisonOperator operator, LiteralPointer<?> pointer) {
        return addExpression(comparisonExpressionFactory.create(targetFieldPointer, operator, pointer));
    }

    private CB is(ComparisonOperator operator, FieldPointer<?, T> pointer) {
        return addExpression(comparisonExpressionFactory.create(targetFieldPointer, operator, pointer));
    }

    protected CB addExpression(Expression<T> expression) {
        CB conjunctionBuilder = createConjunctionBuilder();
        builder.appendExpression(expression);

        return conjunctionBuilder;
    }

    protected abstract CB createConjunctionBuilder();
}