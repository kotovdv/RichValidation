package com.jquartz.rich.validation.core.annotations;


import com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator;

import java.lang.annotation.*;

/**
 * @author Dmitriy Kotov
 */
@Target(ElementType.FIELD)
@Repeatable(Validation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    ComparisonOperator is();

    String thanField() default "";

    String thanValue() default "";
}