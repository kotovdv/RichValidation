package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.subject.SingleFieldSubject;
import com.jquartz.rich.validation.core.subject.TwoFieldsSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RichValidationBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class ComparisonOperatorsIntegrationTest {

    private static final String FIELD = SingleFieldSubject.getFieldName();
    private static final String FIRST_FIELD = TwoFieldsSubject.getFirstFieldName();
    private static final String SECOND_FIELD = TwoFieldsSubject.getSecondFieldName();

    @DataProvider
    public static Object[][] fieldToLiteralScenarios() {
        return new Object[][]{
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 15, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 15, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 15, true}
        };
    }

    @DataProvider
    public static Object[][] fieldToFieldScenarios() {
        return new Object[][]{
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 15, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 15, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 15, true}
        };
    }

    @Test
    @UseDataProvider("fieldToLiteralScenarios")
    public void testLiteralComparisonOperations(ValidationLogic<SingleFieldSubject> logic, int fieldValue, boolean expectedResult) throws Exception {
        assertThat(logic.verify(new SingleFieldSubject(fieldValue))).isEqualTo(expectedResult);
    }

    @Test
    @UseDataProvider("fieldToFieldScenarios")
    public void testLiteralComparisonOperations(ValidationLogic<TwoFieldsSubject> logic, int firstField, int secondField, boolean expectedResult) throws Exception {
        assertThat(logic.verify(new TwoFieldsSubject(firstField, secondField))).isEqualTo(expectedResult);
    }
}