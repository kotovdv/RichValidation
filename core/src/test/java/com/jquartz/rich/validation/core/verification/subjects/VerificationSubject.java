package com.jquartz.rich.validation.core.verification.subjects;

import com.jquartz.rich.validation.core.annotations.Validate;

import static com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator.GREATER_THAN;

/**
 * @author Dmitriy Kotov
 */
public class VerificationSubject {

    @Validate(is = GREATER_THAN, thanField = "secondField")
    private final long firstField;
    @Validate(is = GREATER_THAN, thanField = "thirdField")
    private final long secondField;
    @Validate(is = GREATER_THAN, thanValue = "0")
    private final long thirdField;

    public VerificationSubject(long firstField, long secondField, long thirdField) {
        this.firstField = firstField;
        this.secondField = secondField;
        this.thirdField = thirdField;
    }
}