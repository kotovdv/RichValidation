package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.subject.SingleFieldSubject;
import com.jquartz.rich.validation.core.subject.TwoFieldsSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RichValidationBuilder.ensureThat;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.FALSE;
import static com.jquartz.rich.validation.core.evaluation.TruthValue.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class ComparisonOperatorsIntegrationTest {

    private static final String FIELD = SingleFieldSubject.getFieldName();
    private static final String FIRST_FIELD = TwoFieldsSubject.getFirstFieldName();
    private static final String SECOND_FIELD = TwoFieldsSubject.getSecondFieldName();

    @DataProvider
    public static Object[][] fieldToLiteralScenarios() {
        return new Object[][]{
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 5, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 10, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).build(), 15, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 5, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 10, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).build(), 15, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 15, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 10, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).build(), 5, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 15, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 10, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).build(), 5, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 5, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 10, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).build(), 15, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 5, TRUE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 10, FALSE},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).build(), 15, TRUE}
        };
    }

    @DataProvider
    public static Object[][] fieldToFieldScenarios() {
        return new Object[][]{
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 5, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 10, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).build(), 10, 15, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 5, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 10, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).build(), 10, 15, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 15, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 10, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).build(), 10, 5, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 15, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 10, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).build(), 10, 5, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 5, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 10, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).build(), 10, 15, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 5, TRUE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 10, FALSE},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).build(), 10, 15, TRUE}
        };
    }

    @Test
    @UseDataProvider("fieldToLiteralScenarios")
    public void testLiteralComparisonOperations(ValidationLogic<SingleFieldSubject> logic, int fieldValue, TruthValue expectedResult) throws Exception {
        assertThat(logic.verify(new SingleFieldSubject(fieldValue))).isEqualTo(expectedResult);
    }

    @Test
    @UseDataProvider("fieldToFieldScenarios")
    public void testLiteralComparisonOperations(ValidationLogic<TwoFieldsSubject> logic, int firstField, int secondField, TruthValue expectedResult) throws Exception {
        assertThat(logic.verify(new TwoFieldsSubject(firstField, secondField))).isEqualTo(expectedResult);
    }
}