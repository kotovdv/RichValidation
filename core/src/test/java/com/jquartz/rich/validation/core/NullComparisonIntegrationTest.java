package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.subject.NullableFieldSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RichValidationBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class NullComparisonIntegrationTest {

    private static final String FIELD = NullableFieldSubject.getFieldName();

    @DataProvider
    public static Object[][] nullScenarios() {
        return new Object[][]{
                {null, 10, TruthValue.UNKNOWN},
                {null, null, TruthValue.UNKNOWN},
                {10, null, TruthValue.UNKNOWN},
        };
    }

    @DataProvider
    public static Object[][] nullAssertionScenarios() {
        ValidationLogic<NullableFieldSubject> isNullRule = ensureThat(NullableFieldSubject.class)
                .field(FIELD)
                .isNull()
                .build();

        ValidationLogic<NullableFieldSubject> isNotNullRule = ensureThat(NullableFieldSubject.class)
                .field(FIELD)
                .isNotNull()
                .build();

        return new Object[][]{
                {isNullRule, new NullableFieldSubject(null), TruthValue.TRUE},
                {isNullRule, new NullableFieldSubject(1), TruthValue.FALSE},
                {isNotNullRule, new NullableFieldSubject(null), TruthValue.FALSE},
                {isNotNullRule, new NullableFieldSubject(1), TruthValue.TRUE},
        };
    }

    @Test
    @UseDataProvider("nullScenarios")
    public void testLiteralComparisonOperations(Integer fieldValue, Integer ruleValue, TruthValue expectedResult) throws Exception {
        ValidationLogic<NullableFieldSubject> validationLogic = ensureThat(NullableFieldSubject.class)
                .field(FIELD)
                .isGreaterThan(ruleValue)
                .build();


        NullableFieldSubject subject = new NullableFieldSubject(fieldValue);

        assertThat(validationLogic.verify(subject)).isEqualTo(expectedResult);
    }

    @Test
    @UseDataProvider("nullAssertionScenarios")
    public void testNullabilityAssertion(ValidationLogic<NullableFieldSubject> rule, NullableFieldSubject subject, TruthValue expectedValue) {
        assertThat(rule.verify(subject)).isEqualTo(expectedValue);
    }
}