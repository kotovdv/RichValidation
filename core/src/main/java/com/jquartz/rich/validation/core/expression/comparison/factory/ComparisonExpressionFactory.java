package com.jquartz.rich.validation.core.expression.comparison.factory;

import com.jquartz.rich.validation.core.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.expression.comparison.factory.exception.IncomparableClassesException;
import com.jquartz.rich.validation.core.expression.comparison.factory.exception.NotComparableClassException;
import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.Transformation;
import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.TransformationLogic;
import com.jquartz.rich.validation.core.expression.comparison.factory.transformation.TransformedComparableValue;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.expression.value.FieldValue;
import com.jquartz.rich.validation.core.expression.value.LiteralValue;
import com.jquartz.rich.validation.core.pointer.FieldPointer;
import com.jquartz.rich.validation.core.pointer.LiteralPointer;
import com.jquartz.rich.validation.core.util.PrimitiveToWrapperConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.jquartz.rich.validation.core.expression.comparison.factory.transformation.concrete.NumberTransformer.createTransformation;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonExpressionFactory {

    private final PrimitiveToWrapperConverter toWrapperConverter = new PrimitiveToWrapperConverter();
    private final TransformationLogic<Long> numberToLong = createTransformation(Number::longValue, Long.class);
    private final TransformationLogic<BigDecimal> numberToBigDecimal = createTransformation(number -> new BigDecimal(number.toString()), BigDecimal.class);
    private final TransformationLogic<BigInteger> numberToBigInteger = createTransformation(number -> new BigInteger(number.toString()), BigInteger.class);

    public <T extends Comparable<T>, S> ComparisonExpression<T, S> create(FieldPointer<?, S> left,
                                                                          ComparisonOperator operator,
                                                                          LiteralPointer<?> right) {
        TransformationLogic<T> logic = generateTransformationLogic(
                left.getTargetClass(),
                right.getTargetClass().orElse(left.getTargetClass())
        );

        return new ComparisonExpression<>(
                new TransformedComparableValue<>(new FieldValue<>(left), logic.getLeftTransformation()),
                operator,
                new TransformedComparableValue<>(new LiteralValue<>(right), logic.getRightTransformation())
        );
    }

    public <T extends Comparable<T>, S> ComparisonExpression<T, S> create(FieldPointer<?, S> left,
                                                                          ComparisonOperator operator,
                                                                          FieldPointer<?, S> right) {
        TransformationLogic<T> logic = generateTransformationLogic(
                left.getTargetClass(),
                right.getTargetClass()
        );

        return new ComparisonExpression<>(
                new TransformedComparableValue<>(new FieldValue<>(left), logic.getLeftTransformation()),
                operator,
                new TransformedComparableValue<>(new FieldValue<>(right), logic.getRightTransformation())
        );
    }

    private <T extends Comparable<T>> TransformationLogic<T> generateTransformationLogic(final Class<?> initialLeftClass, final Class<?> initialRightClass) {
        Class<?> left = wrapPrimitive(initialLeftClass);
        Class<?> right = wrapPrimitive(initialRightClass);

        ensureThatClassesImplementComparable(left, right);

        if (left.equals(right)) {
            return emptyTransformation(left);
        }

        if (Number.class.isAssignableFrom(left)) {
            if (Number.class.isAssignableFrom(right)) {
                return isFloatingPointNumber(left) || isFloatingPointNumber(right)
                        ? transformToFloatingPointNumber()
                        : transformToIntegerNumber(left, right);
            }
        }

        throw new IncomparableClassesException(initialLeftClass, initialRightClass);
    }

    private boolean isFloatingPointNumber(Class<?> targetClass) {
        return Float.class.equals(targetClass) || Double.class.equals(targetClass) || BigDecimal.class.equals(targetClass);
    }

    private void ensureThatClassesImplementComparable(Class<?> left, Class<?> right) {
        if (isNotComparable(left)) {
            throw new NotComparableClassException(left);
        }

        if (isNotComparable(right)) {
            throw new NotComparableClassException(right);
        }
    }

    private boolean isNotComparable(Class<?> left) {
        return !Comparable.class.isAssignableFrom(left);
    }

    private Class<?> wrapPrimitive(Class<?> initialClass) {
        return initialClass.isPrimitive()
                ? toWrapperConverter.getWrapperFor(initialClass)
                : initialClass;
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> TransformationLogic<T> emptyTransformation(Class<?> targetClass) {
        return new TransformationLogic(new Transformation<>(targetClass), new Transformation<>(targetClass));
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> TransformationLogic<T> transformToIntegerNumber(Class<?> left, Class<?> right) {
        return BigInteger.class.equals(left) || BigInteger.class.equals(right)
                ? (TransformationLogic<T>) numberToBigInteger
                : (TransformationLogic<T>) numberToLong;
    }

    @SuppressWarnings("unchecked")
    private <T extends Comparable<T>> TransformationLogic<T> transformToFloatingPointNumber() {
        return (TransformationLogic<T>) numberToBigDecimal;
    }
}