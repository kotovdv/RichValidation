package com.jquartz.rich.validation.core.expression.conditional;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.base.binary.LiteralToLiteralBinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.comparison.ComparisonAction;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.pointer.literal.PlainLiteralPointer;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator.GREATER_THAN;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class ConditionalExpressionTest {

    @DataProvider
    public static Object[][] conditionExpressionDP() {
        Class<Integer> comparableType = Integer.class;
        LiteralPointer<Integer> ten = new PlainLiteralPointer<>(10, comparableType);
        LiteralPointer<Integer> five = new PlainLiteralPointer<>(5, comparableType);
        LiteralPointer<Integer> nullValue = new PlainLiteralPointer<>(null, null);

        Expression<Object> validExpression = new LiteralToLiteralBinaryExpression(ten, new ComparisonAction<>(GREATER_THAN, comparableType), five);
        Expression<Object> invalidExpression = new LiteralToLiteralBinaryExpression(five, new ComparisonAction<>(GREATER_THAN, comparableType), ten);
        Expression<Object> unknownExpression = new LiteralToLiteralBinaryExpression(five, new ComparisonAction<>(GREATER_THAN, comparableType), nullValue);

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
    public void testConditionalExpression(Expression<Object> condition,
                                          Expression<Object> target,
                                          TruthValue expectedConditionResult,
                                          TruthValue expectedTargetResult) throws Exception {
        Object mockSubject = new Object();
        ConditionalExpression<Object> conditionalExpression = new ConditionalExpression<>(condition, target);

        assertThat(conditionalExpression.isApplicable(mockSubject)).isEqualTo(expectedConditionResult);
        assertThat(conditionalExpression.apply(mockSubject)).isEqualTo(expectedTargetResult);
    }
}