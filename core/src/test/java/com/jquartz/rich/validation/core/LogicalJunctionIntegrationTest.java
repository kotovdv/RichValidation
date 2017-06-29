package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.subject.SingleFieldSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.FALSE;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class LogicalJunctionIntegrationTest {

    private static final String FIELD = SingleFieldSubject.getFieldName();

    @DataProvider
    public static Object[][] logicalJunctionScenarios() {
        Rule<SingleFieldSubject> isEqualToOneOfTwoNumbers = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isCoequalTo(10)
                .or()
                .isCoequalTo(20)
                .build();

        Rule<SingleFieldSubject> isInRangeBetween = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterOrCoequalTo(10)
                .and()
                .isLessOrCoequalTo(20)
                .build();

        Rule<SingleFieldSubject> isInImpossibleRange = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterThan(20)
                .and()
                .isLessThan(10)
                .build();

        Rule<SingleFieldSubject> isInNowPossibleRange = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterOrCoequalTo(20)
                .or()
                .isLessOrCoequalTo(10)
                .build();

        return new Object[][]{
                {isEqualToOneOfTwoNumbers, 5, FALSE},
                {isEqualToOneOfTwoNumbers, 10, TRUE},
                {isEqualToOneOfTwoNumbers, 15, FALSE},
                {isEqualToOneOfTwoNumbers, 20, TRUE},
                {isEqualToOneOfTwoNumbers, 25, FALSE},
                {isInRangeBetween, 5, FALSE},
                {isInRangeBetween, 10, TRUE},
                {isInRangeBetween, 15, TRUE},
                {isInRangeBetween, 20, TRUE},
                {isInRangeBetween, 25, FALSE},
                {isInImpossibleRange, 5, FALSE},
                {isInImpossibleRange, 10, FALSE},
                {isInImpossibleRange, 15, FALSE},
                {isInImpossibleRange, 20, FALSE},
                {isInImpossibleRange, 25, FALSE},
                {isInNowPossibleRange, 5, TRUE},
                {isInNowPossibleRange, 10, TRUE},
                {isInNowPossibleRange, 15, FALSE},
                {isInNowPossibleRange, 20, TRUE},
                {isInNowPossibleRange, 25, TRUE},
        };
    }

    @Test
    @UseDataProvider("logicalJunctionScenarios")
    public void testLiteralComparisonOperations(Rule<SingleFieldSubject> rule, int fieldValue, TruthValue expectedResult) throws Exception {
        assertThat(rule.validate(new SingleFieldSubject(fieldValue))).isEqualTo(expectedResult);
    }
}