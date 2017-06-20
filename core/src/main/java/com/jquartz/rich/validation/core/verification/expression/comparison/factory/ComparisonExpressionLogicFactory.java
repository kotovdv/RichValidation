package com.jquartz.rich.validation.core.verification.expression.comparison.factory;

import com.jquartz.rich.validation.core.api.FieldPointer;
import com.jquartz.rich.validation.core.api.LiteralPointer;
import com.jquartz.rich.validation.core.exception.api.IncomparableClassesException;
import com.jquartz.rich.validation.core.verification.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.verification.expression.comparison.factory.util.ComparableValueTransformer;
import com.jquartz.rich.validation.core.verification.expression.comparison.factory.util.PrimitiveToWrapperConverter;
import com.jquartz.rich.validation.core.verification.expression.comparison.factory.util.Transformation;
import com.jquartz.rich.validation.core.verification.expression.comparison.factory.util.TransformationLogic;
import com.jquartz.rich.validation.core.verification.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.comparison.value.FieldValue;
import com.jquartz.rich.validation.core.verification.expression.comparison.value.LiteralValue;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonExpressionLogicFactory {

    private final PrimitiveToWrapperConverter toWrapperConverter = new PrimitiveToWrapperConverter();

    public <T extends Comparable<T>, S> ComparisonExpression<T, S> create(FieldPointer<?, S> left, ComparisonOperator operator, LiteralPointer<?> right) {
        Class<?> leftPointerClass = left.getTargetClass();
        Class<?> rightPointerClass = right.getTargetClass().orElse(leftPointerClass);

        TransformationLogic<T> logic = generateTransformationLogic(leftPointerClass, rightPointerClass);

        return new ComparisonExpression<>(
                new ComparableValueTransformer<>(new FieldValue<>(left), logic.getLeftTransformation()),
                operator,
                new ComparableValueTransformer<>(new LiteralValue<>(right), logic.getRightTransformation()));
    }

    private <T extends Comparable<T>> TransformationLogic<T> generateTransformationLogic(final Class<?> initialLeftClass, final Class<?> initialRightClass) {
        Class<?> left = wrapPrimitive(initialLeftClass);
        Class<?> right = wrapPrimitive(initialRightClass);

        if (isNotComparable(left) || isNotComparable(right)) {
            throw new RuntimeException("Not comparable classes"); //TODO think about dis
        }

        if (left.equals(right)) {
            return emptyTransformation(left);
        }

        if (Number.class.isAssignableFrom(left)) {
            if (Number.class.isAssignableFrom(right)) {
                return transformBothToLong();
            }
        }

        throw new IncomparableClassesException(initialLeftClass, initialRightClass);
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> TransformationLogic<T> emptyTransformation(Class<?> left) {
        return new TransformationLogic(new Transformation<>(left), new Transformation<>(left));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> TransformationLogic<T> transformBothToLong() {
        List<Function<?, ?>> leftOperandTransformation = new LinkedList<>();
        List<Function<?, ?>> rightOperandTransformation = new LinkedList<>();

        leftOperandTransformation.add(currentValue -> Number.class.cast(currentValue).longValue());
        rightOperandTransformation.add(currentValue -> Number.class.cast(currentValue).longValue());

        return new TransformationLogic(new Transformation(Long.class, leftOperandTransformation), new Transformation(Long.class, rightOperandTransformation));
    }

    private boolean isNotComparable(Class<?> left) {
        return !Comparable.class.isAssignableFrom(left);
    }

    private Class<?> wrapPrimitive(Class<?> initialClass) {
        return initialClass.isPrimitive() ? toWrapperConverter.getWrapperFor(initialClass) : initialClass;
    }
}
