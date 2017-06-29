package com.jquartz.rich.validation.core.expression.comparison.factory;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.TrustworthinessStub;
import com.jquartz.rich.validation.core.expression.Expression;
import com.jquartz.rich.validation.core.pointer.field.FieldPointer;
import com.jquartz.rich.validation.core.pointer.field.PlainFieldPointer;
import com.jquartz.rich.validation.core.pointer.literal.LiteralPointer;
import com.jquartz.rich.validation.core.pointer.literal.PlainLiteralPointer;
import com.jquartz.rich.validation.core.rule.ClassField;
import com.jquartz.rich.validation.core.subject.FloatingPointSubject;
import org.junit.Test;

import java.lang.reflect.Field;

import static com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator.EQUAL_TO;
import static com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator.NOT_EQUAL_TO;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparisonExpressionFactoryTest {

    private static String FIELD = FloatingPointSubject.getFieldName();
    private final ComparisonExpressionFactory comparisonExpressionFactory = new ComparisonExpressionFactory();

    /**
     * Describes test case from Issue#5 description
     * <p>
     * https://github.com/kotovdv/RichValidation/issues/5
     */
    @Test
    public void testFloatToDoubleComparison() throws Exception {
        FloatingPointSubject subject = new FloatingPointSubject(10.4);
        FieldPointer<Double, FloatingPointSubject> fieldPointer = createFieldPointer();
        LiteralPointer<Float> literalPointer = new PlainLiteralPointer<>(10.5F, Float.TYPE);


        Expression<FloatingPointSubject> expression = comparisonExpressionFactory.create(
                fieldPointer,
                NOT_EQUAL_TO,
                literalPointer);

        assertThat(expression.apply(subject, TrustworthinessStub.INSTANCE)).isEqualTo(TruthValue.TRUE);
    }


    /**
     * Describes a case when float is converted to double
     * <p>
     * new Float(0.1f).doubleValue() - will not result in 0.1D, but instead will be something like: 0.10000000149011612
     * <p>
     * To be compared correctly they both have to be promoted to BigDecimal during execution
     */
    @Test
    public void testFloatWithDoubleComparison() throws Exception {
        FloatingPointSubject subject = new FloatingPointSubject(0.1D);
        FieldPointer<Double, FloatingPointSubject> fieldPointer = createFieldPointer();
        PlainLiteralPointer<Float> literalPointer = new PlainLiteralPointer<>(0.1F, Float.TYPE);

        Expression<FloatingPointSubject> expression = comparisonExpressionFactory.create(
                fieldPointer,
                EQUAL_TO,
                literalPointer);

        assertThat(expression.apply(subject, TrustworthinessStub.INSTANCE)).isEqualTo(TruthValue.TRUE);
    }

    private FieldPointer<Double, FloatingPointSubject> createFieldPointer() throws NoSuchFieldException {
        Class<FloatingPointSubject> targetClass = FloatingPointSubject.class;
        Field field = targetClass.getDeclaredField(FIELD);
        field.setAccessible(true);

        return new PlainFieldPointer<>(new ClassField<>(targetClass, Double.TYPE, field.getName()), field);
    }
}