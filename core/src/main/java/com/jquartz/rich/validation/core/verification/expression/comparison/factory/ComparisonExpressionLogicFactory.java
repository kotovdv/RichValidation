package com.jquartz.rich.validation.core.verification.expression.comparison.factory;

import com.jquartz.rich.validation.core.api.FieldPointer;
import com.jquartz.rich.validation.core.api.LiteralPointer;
import com.jquartz.rich.validation.core.verification.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.verification.expression.comparison.operator.ComparisonOperator;

/**
 * @author Dmitriy Kotov
 */
public class ComparisonExpressionLogicFactory {


    public <T extends Comparable<T>> ComparisonExpression<T, ?> create(FieldPointer<?, ?> left,
                                                                       ComparisonOperator operator,
                                                                       LiteralPointer<?> right) {

//        left.resolve();


        return null;


    }


}
