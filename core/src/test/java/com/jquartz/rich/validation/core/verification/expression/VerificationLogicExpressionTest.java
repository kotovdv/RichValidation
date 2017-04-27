package com.jquartz.rich.validation.core.verification.expression;

import com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.verification.expression.value.LiteralValue;
import org.junit.Test;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogicExpressionTest {

    @Test
    public void name() throws Exception {
        VerificationLogicExpression<Integer> expression = new VerificationLogicExpression<>(
                new LiteralValue<>(10),
                new LiteralValue<>(15),
                ComparisonOperator.EQUAL_TO);

//        expression.apply();
    }
}