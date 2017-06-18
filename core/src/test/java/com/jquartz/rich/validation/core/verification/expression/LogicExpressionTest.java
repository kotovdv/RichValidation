package com.jquartz.rich.validation.core.verification.expression;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.verification.expression.operator.ComparisonOperator.*;
import static com.jquartz.rich.validation.core.verification.expression.value.LiteralValue.literal;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class LogicExpressionTest {

    private static final Integer STUB_SOURCE = 1;

    @DataProvider
    public static Object[][] scenarios() {
        return new Object[][]{
                {new LogicExpression<>(literal(10), EQUAL_TO, literal(15)), false},
                {new LogicExpression<>(literal(10), NOT_EQUAL_TO, literal(15)), true},
                {new LogicExpression<>(literal(10), GREATER_THAN, literal(15)), false},
                {new LogicExpression<>(literal(10), LESS_THAN, literal(15)), true},
        };
    }

    @Test
    @UseDataProvider("scenarios")
    public void testLogicExpressionEvaluation(LogicExpression<Integer> expression, boolean expectedResult) throws Exception {
        assertThat(expression.apply(STUB_SOURCE)).isEqualTo(expectedResult);
    }
}