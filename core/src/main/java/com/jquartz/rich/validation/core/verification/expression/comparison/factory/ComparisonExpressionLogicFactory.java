package com.jquartz.rich.validation.core.verification.expression.comparison.factory;

import com.jquartz.rich.validation.core.api.FieldPointer;
import com.jquartz.rich.validation.core.api.LiteralPointer;
import com.jquartz.rich.validation.core.exception.api.IncomparableClassesException;
import com.jquartz.rich.validation.core.verification.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.verification.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.comparison.value.FieldValue;
import com.jquartz.rich.validation.core.verification.expression.comparison.value.LiteralValue;

import java.util.Optional;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonExpressionLogicFactory {


    public <T extends Comparable<T>, S> ComparisonExpression<T, S> create(FieldPointer<?, S> left,
                                                                          ComparisonOperator operator,
                                                                          LiteralPointer<?> right) {
        Class<?> leftPointerClass = left.getTargetClass();
        Optional<Class<?>> rightPointerClass = right.getTargetClass();

        Class<?> primaryClass = selectPrimaryClass(leftPointerClass, rightPointerClass.orElse(leftPointerClass));

        //TODO decide target class for both of them

//        left.resolve();


        return new ComparisonExpression(new FieldValue<>(left), operator, new LiteralValue<>(right));
    }

    //TODO add check if these classes implement comparable!
    private Class<?> selectPrimaryClass(Class<?> leftClass, Class<?> rightClass) {
        if (leftClass.equals(rightClass)) {
            return leftClass;
        }

        if (leftClass.isAssignableFrom(Number.class)) {
            if (rightClass.isAssignableFrom(Number.class)) {
                //TODO select biggest type among them and cast them to it...
            }
        }

        throw new IncomparableClassesException(leftClass, rightClass);
    }
}
