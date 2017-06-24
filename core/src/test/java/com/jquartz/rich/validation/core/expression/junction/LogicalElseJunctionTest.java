package com.jquartz.rich.validation.core.expression.junction;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.common.IsNullExpression;
import com.jquartz.rich.validation.core.expression.comparison.ComparisonExpression;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.expression.conditional.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.value.LiteralValue;
import com.jquartz.rich.validation.core.pointer.LiteralPointer;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class LogicalElseJunctionTest {

    @DataProvider
    public static Object[][] conditionExpressionDP() {
        LiteralValue<Integer, Object> ten = new LiteralValue<>(new LiteralPointer<>(10));
        LiteralValue<Integer, Object> five = new LiteralValue<>(new LiteralPointer<>(5));
        LiteralValue<Integer, Object> nullValue = new LiteralValue<>(new LiteralPointer<>(null));

        ComparisonExpression<Integer, Object> validExpression = new ComparisonExpression<>(ten, ComparisonOperator.GREATER_THAN, five);
        ComparisonExpression<Integer, Object> invalidExpression = new ComparisonExpression<>(five, ComparisonOperator.GREATER_THAN, ten);
        ComparisonExpression<Integer, Object> unknownExpression = new ComparisonExpression<>(five, ComparisonOperator.GREATER_THAN, nullValue);


        ConditionalExpression<Object> applicableAndTrue = new ConditionalExpression<>(validExpression, validExpression);
        ConditionalExpression<Object> applicableAndFalse = new ConditionalExpression<>(validExpression, invalidExpression);
        ConditionalExpression<Object> applicableAndUnknown = new ConditionalExpression<>(validExpression, unknownExpression);
        ConditionalExpression<Object> notApplicable = new ConditionalExpression<>(invalidExpression, unknownExpression);
        ConditionalExpression<Object> unknownApplicability = new ConditionalExpression<>(unknownExpression, unknownExpression);

        return new Object[][]{
                {new LogicalElseJunction<>(asList(applicableAndTrue, applicableAndTrue)), TRUE},
                {new LogicalElseJunction<>(asList(applicableAndFalse, applicableAndFalse)), FALSE},
                {new LogicalElseJunction<>(asList(unknownApplicability, unknownApplicability)), UNKNOWN},

                {new LogicalElseJunction<>(asList(applicableAndFalse, applicableAndTrue)), FALSE},
                {new LogicalElseJunction<>(asList(applicableAndTrue, applicableAndFalse)), TRUE},

                {new LogicalElseJunction<>(asList(unknownApplicability, applicableAndTrue)), TRUE},
                {new LogicalElseJunction<>(asList(applicableAndTrue, unknownApplicability)), TRUE},

                {new LogicalElseJunction<>(asList(unknownApplicability, applicableAndFalse)), FALSE},
                {new LogicalElseJunction<>(asList(applicableAndFalse, unknownApplicability)), FALSE},

                {new LogicalElseJunction<>(asList(unknownApplicability, applicableAndUnknown)), UNKNOWN},
                {new LogicalElseJunction<>(asList(applicableAndUnknown, unknownApplicability)), UNKNOWN},

                {new LogicalElseJunction<>(asList(notApplicable, applicableAndTrue)), TRUE},
                {new LogicalElseJunction<>(asList(applicableAndTrue, notApplicable)), TRUE},

                {new LogicalElseJunction<>(asList(notApplicable, applicableAndFalse)), FALSE},
                {new LogicalElseJunction<>(asList(applicableAndFalse, notApplicable)), FALSE},

                {new LogicalElseJunction<>(asList(notApplicable, applicableAndUnknown)), UNKNOWN},
                {new LogicalElseJunction<>(asList(applicableAndUnknown, notApplicable)), UNKNOWN},

                {new LogicalElseJunction<>(asList(notApplicable, notApplicable)), TRUE}, //Because of otherwise optional
                {new LogicalElseJunction<>(asList(notApplicable, notApplicable), new IsNullExpression<>(new LiteralValue<>(new LiteralPointer<>(null)))), TRUE}, //Because of otherwise isNull
                {new LogicalElseJunction<>(asList(notApplicable, notApplicable), new IsNullExpression<>(new LiteralValue<>(new LiteralPointer<>(new Object())))), FALSE}, //Because of otherwise  isNull
        };
    }

    @Test
    @UseDataProvider("conditionExpressionDP")
    public void testElseJunction(LogicalElseJunction<Object> junction, TruthValue expectedTargetResult) throws Exception {
        Object mockSubject = new Object();

        assertThat(junction.apply(mockSubject)).isEqualTo(expectedTargetResult);
    }
}