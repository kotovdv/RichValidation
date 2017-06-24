package com.jquartz.rich.validation.core.expression.conditional;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.expression.value.LiteralValue;
import com.jquartz.rich.validation.core.pointer.LiteralPointer;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class ConditionalExpressionTest {

    @DataProvider
    public static Object[][] conditionExpressionDP() {
        LiteralValue<Integer, Object> ten = new LiteralValue<>(new LiteralPointer<>(10));
        LiteralValue<Integer, Object> five = new LiteralValue<>(new LiteralPointer<>(5));
        LiteralValue<Integer, Object> nullValue = new LiteralValue<>(new LiteralPointer<>(null));

        ComparisonExpression<Integer, Object> validExpression = new ComparisonExpression<>(ten, ComparisonOperator.GREATER_THAN, five);
        ComparisonExpression<Integer, Object> invalidExpression = new ComparisonExpression<>(five, ComparisonOperator.GREATER_THAN, ten);
        ComparisonExpression<Integer, Object> unknownExpression = new ComparisonExpression<>(five, ComparisonOperator.GREATER_THAN, nullValue);

        return new Object[][]{
                {validExpression, invalidExpression, TruthValue.TRUE, TruthValue.FALSE},
                {invalidExpression, validExpression, TruthValue.FALSE, TruthValue.TRUE},
                {unknownExpression, validExpression, TruthValue.UNKNOWN, TruthValue.TRUE},
                {unknownExpression, invalidExpression, TruthValue.UNKNOWN, TruthValue.FALSE},
                {validExpression, unknownExpression, TruthValue.TRUE, TruthValue.UNKNOWN},
                {invalidExpression, unknownExpression, TruthValue.FALSE, TruthValue.UNKNOWN},
        };
    }

    @Test
    @UseDataProvider("conditionExpressionDP")
    public void testConditionalExpression(ComparisonExpression<Integer, Object> condition,
                                          ComparisonExpression<Integer, Object> target,
                                          TruthValue expectedConditionResult,
                                          TruthValue expectedTargetResult) throws Exception {
        Object mockSubject = new Object();
        ConditionalExpression<Object> conditionalExpression = new ConditionalExpression<>(condition, target);

        assertThat(conditionalExpression.isApplicable(mockSubject)).isEqualTo(expectedConditionResult);
        assertThat(conditionalExpression.apply(mockSubject)).isEqualTo(expectedTargetResult);
    }
}