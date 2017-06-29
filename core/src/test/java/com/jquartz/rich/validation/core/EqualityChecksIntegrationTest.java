package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.subject.EqualityTestSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class EqualityChecksIntegrationTest {

    @DataProvider
    public static Object[][] fieldToFieldEqualityScenarios() {
        Rule<EqualityTestSubject> positiveFieldToFieldRule = ensureThat(EqualityTestSubject.class)
                .field(EqualityTestSubject.FIRST_FIELD)
                .isEqualToField(EqualityTestSubject.SECOND_FIELD)
                .build();

        Rule<EqualityTestSubject> negativeFieldToFieldRule = ensureThat(EqualityTestSubject.class)
                .field(EqualityTestSubject.FIRST_FIELD)
                .isNotEqualToField(EqualityTestSubject.SECOND_FIELD)
                .build();

        return new Object[][]{
                {positiveFieldToFieldRule, new EqualityTestSubject(new Object(), new Object()), TruthValue.FALSE},
                {positiveFieldToFieldRule, new EqualityTestSubject(10L, 10), TruthValue.FALSE},
                {positiveFieldToFieldRule, new EqualityTestSubject(10, 10), TruthValue.TRUE},
                {positiveFieldToFieldRule, new EqualityTestSubject(null, null), TruthValue.TRUE},
                {positiveFieldToFieldRule, new EqualityTestSubject(10, null), TruthValue.FALSE},
                {positiveFieldToFieldRule, new EqualityTestSubject(null, 10), TruthValue.FALSE},

                {negativeFieldToFieldRule, new EqualityTestSubject(new Object(), new Object()), TruthValue.TRUE},
                {negativeFieldToFieldRule, new EqualityTestSubject(10L, 10), TruthValue.TRUE},
                {negativeFieldToFieldRule, new EqualityTestSubject(10, 10), TruthValue.FALSE},
                {negativeFieldToFieldRule, new EqualityTestSubject(null, null), TruthValue.FALSE},
                {negativeFieldToFieldRule, new EqualityTestSubject(10, null), TruthValue.TRUE},
                {negativeFieldToFieldRule, new EqualityTestSubject(null, 10), TruthValue.TRUE},
        };
    }

    @DataProvider
    public static Object[][] fieldToLiteralEqualityPositiveScenarios() {
        return new Object[][]{
                {new EqualityTestSubject(new Object(), new Object()), TruthValue.FALSE},
                {new EqualityTestSubject(10L, 10), TruthValue.FALSE},
                {new EqualityTestSubject(10, 10), TruthValue.TRUE},
                {new EqualityTestSubject(null, null), TruthValue.TRUE},
                {new EqualityTestSubject(10, null), TruthValue.FALSE},
                {new EqualityTestSubject(null, 10), TruthValue.FALSE},
        };
    }

    @DataProvider
    public static Object[][] fieldToLiteralEqualityNegativeScenarios() {
        return new Object[][]{
                {new EqualityTestSubject(new Object(), new Object()), TruthValue.TRUE},
                {new EqualityTestSubject(10L, 10), TruthValue.TRUE},
                {new EqualityTestSubject(10, 10), TruthValue.FALSE},
                {new EqualityTestSubject(null, null), TruthValue.FALSE},
                {new EqualityTestSubject(10, null), TruthValue.TRUE},
                {new EqualityTestSubject(null, 10), TruthValue.TRUE},
        };
    }

    @Test
    @UseDataProvider("fieldToFieldEqualityScenarios")
    public void testEquality(Rule<EqualityTestSubject> rule, EqualityTestSubject subject, TruthValue expectedResult) throws Exception {
        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }

    @Test
    @UseDataProvider("fieldToLiteralEqualityPositiveScenarios")
    public void testFieldToLiteralPositiveEquality(EqualityTestSubject subject, TruthValue expectedResult) throws Exception {
        Rule<EqualityTestSubject> rule = ensureThat(EqualityTestSubject.class)
                .field(EqualityTestSubject.FIRST_FIELD)
                .isEqualTo(subject.getSecondField())
                .build();

        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }

    @Test
    @UseDataProvider("fieldToLiteralEqualityNegativeScenarios")
    public void testFieldToLiteralNegativeEquality(EqualityTestSubject subject, TruthValue expectedResult) throws Exception {
        Rule<EqualityTestSubject> rule = ensureThat(EqualityTestSubject.class)
                .field(EqualityTestSubject.FIRST_FIELD)
                .isNotEqualTo(subject.getSecondField())
                .build();

        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }
}