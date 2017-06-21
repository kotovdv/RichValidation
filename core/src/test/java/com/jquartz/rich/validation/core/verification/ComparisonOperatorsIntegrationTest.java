package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.subjects.SingleFieldSubject;
import com.jquartz.rich.validation.core.verification.subjects.TwoFieldsSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.verification.builder.VerificationLogicBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class ComparisonOperatorsIntegrationTest {

    private static final String FIELD = SingleFieldSubject.getFieldName();

    @DataProvider
    public static Object[][] fieldToLiteralScenarios() {
        return new Object[][]{
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).create(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).create(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterThan(10).create(), 15, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).create(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).create(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isGreaterOrEqualTo(10).create(), 15, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).create(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).create(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessThan(10).create(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).create(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).create(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isLessOrEqualTo(10).create(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).create(), 5, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).create(), 10, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isEqualTo(10).create(), 15, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).create(), 5, true},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).create(), 10, false},
                {ensureThat(SingleFieldSubject.class).field(FIELD).isNotEqualTo(10).create(), 15, true}
        };
    }

    @Test
    @UseDataProvider("fieldToLiteralScenarios")
    public void testLiteralComparisonOperations(VerificationLogic<SingleFieldSubject> logic, int fieldValue, boolean expectedResult) throws Exception {
        assertThat(logic.verify(new SingleFieldSubject(fieldValue))).isEqualTo(expectedResult);
    }

    private static final String FIRST_FIELD = TwoFieldsSubject.getFirstFieldName();
    private static final String SECOND_FIELD = TwoFieldsSubject.getSecondFieldName();

    @DataProvider
    public static Object[][] fieldToFieldScenarios() {
        return new Object[][]{
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).create(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).create(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterThanField(SECOND_FIELD).create(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).create(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).create(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isGreaterOrEqualToField(SECOND_FIELD).create(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).create(), 10, 15, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).create(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessThanField(SECOND_FIELD).create(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).create(), 10, 15, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).create(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isLessOrEqualToField(SECOND_FIELD).create(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).create(), 10, 5, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).create(), 10, 10, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isEqualToField(SECOND_FIELD).create(), 10, 15, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).create(), 10, 5, true},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).create(), 10, 10, false},
                {ensureThat(TwoFieldsSubject.class).field(FIRST_FIELD).isNotEqualToField(SECOND_FIELD).create(), 10, 15, true}
        };
    }

    @Test
    @UseDataProvider("fieldToFieldScenarios")
    public void testLiteralComparisonOperations(VerificationLogic<TwoFieldsSubject> logic, int firstField, int secondField, boolean expectedResult) throws Exception {
        assertThat(logic.verify(new TwoFieldsSubject(firstField, secondField))).isEqualTo(expectedResult);
    }
}