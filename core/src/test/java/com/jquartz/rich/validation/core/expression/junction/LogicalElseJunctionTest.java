package com.jquartz.rich.validation.core.expression.junction;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessStub;
import com.jquartz.rich.validation.core.expression.ConditionalExpression;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.expression.base.binary.LiteralToLiteralBinaryExpression;
import com.jquartz.rich.validation.core.expression.base.binary.action.comparison.ComparisonAction;
import com.jquartz.rich.validation.core.expression.base.unary.LiteralUnaryExpression;
import com.jquartz.rich.validation.core.expression.base.unary.action.nullability.IsNotNullAction;
import com.jquartz.rich.validation.core.expression.base.unary.action.nullability.IsNullAction;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.pointer.literal.PlainLiteralPointer;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.*;
import static com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator.GREATER_THAN;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class LogicalElseJunctionTest {

    @DataProvider
    public static Object[][] conditionExpressionDP() {
        Class<Integer> type = Integer.class;
        LiteralPointer<Integer> ten = new PlainLiteralPointer<>(10, type);
        LiteralPointer<Integer> five = new PlainLiteralPointer<>(5, type);
        LiteralPointer<Integer> nullValue = new PlainLiteralPointer<>(null, null);

        Expression<Object> validExpression = new LiteralToLiteralBinaryExpression(ten, new ComparisonAction<>(GREATER_THAN, type), five);
        Expression<Object> invalidExpression = new LiteralToLiteralBinaryExpression(five, new ComparisonAction<>(GREATER_THAN, type), ten);
        Expression<Object> unknownExpression = new LiteralToLiteralBinaryExpression(five, new ComparisonAction<>(GREATER_THAN, type), nullValue);


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
                {new LogicalElseJunction<>(asList(notApplicable, notApplicable), new LiteralUnaryExpression<>(new PlainLiteralPointer<>(new Object(), Object.class), new IsNotNullAction())), TRUE}, //Because of otherwise isNull
                {new LogicalElseJunction<>(asList(notApplicable, notApplicable), new LiteralUnaryExpression<>(new PlainLiteralPointer<>(new Object(), Object.class), new IsNullAction())), FALSE}, //Because of otherwise  isNull
        };
    }

    @Test
    @UseDataProvider("conditionExpressionDP")
    public void testElseJunction(LogicalElseJunction<Object> junction, TruthValue expectedTargetResult) throws Exception {
        Object mockSubject = new Object();

        assertThat(junction.apply(mockSubject, TrustworthinessStub.INSTANCE)).isEqualTo(expectedTargetResult);
    }
}