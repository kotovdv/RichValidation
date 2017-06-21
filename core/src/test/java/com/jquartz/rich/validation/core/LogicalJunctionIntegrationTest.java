package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.subject.SingleFieldSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RichValidationBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class LogicalJunctionIntegrationTest {

    private static final String FIELD = SingleFieldSubject.getFieldName();

    @DataProvider
    public static Object[][] logicalJunctionScenarios() {
        ValidationLogic<SingleFieldSubject> isEqualToOneOfTwoNumbers = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isEqualTo(10)
                .or()
                .isEqualTo(20)
                .build();

        ValidationLogic<SingleFieldSubject> isInRangeBetween = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterOrEqualTo(10)
                .and()
                .isLessOrEqualTo(20)
                .build();

        ValidationLogic<SingleFieldSubject> isInImpossibleRange = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterThan(20)
                .and()
                .isLessThan(10)
                .build();

        ValidationLogic<SingleFieldSubject> isInNowPossibleRange = ensureThat(SingleFieldSubject.class)
                .field(FIELD)
                .isGreaterOrEqualTo(20)
                .or()
                .isLessOrEqualTo(10)
                .build();

        return new Object[][]{
                {isEqualToOneOfTwoNumbers, 5, false},
                {isEqualToOneOfTwoNumbers, 10, true},
                {isEqualToOneOfTwoNumbers, 15, false},
                {isEqualToOneOfTwoNumbers, 20, true},
                {isEqualToOneOfTwoNumbers, 25, false},
                {isInRangeBetween, 5, false},
                {isInRangeBetween, 10, true},
                {isInRangeBetween, 15, true},
                {isInRangeBetween, 20, true},
                {isInRangeBetween, 25, false},
                {isInImpossibleRange, 5, false},
                {isInImpossibleRange, 10, false},
                {isInImpossibleRange, 15, false},
                {isInImpossibleRange, 20, false},
                {isInImpossibleRange, 25, false},
                {isInNowPossibleRange, 5, true},
                {isInNowPossibleRange, 10, true},
                {isInNowPossibleRange, 15, false},
                {isInNowPossibleRange, 20, true},
                {isInNowPossibleRange, 25, true},
        };
    }

    @Test
    @UseDataProvider("logicalJunctionScenarios")
    public void testLiteralComparisonOperations(ValidationLogic<SingleFieldSubject> logic, int fieldValue, boolean expectedResult) throws Exception {
        assertThat(logic.verify(new SingleFieldSubject(fieldValue))).isEqualTo(expectedResult);
    }
}