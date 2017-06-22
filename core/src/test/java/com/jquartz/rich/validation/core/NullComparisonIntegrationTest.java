package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.subject.NullableFieldSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RichValidationBuilder.ensureThat;

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

    @Test
    @UseDataProvider("nullScenarios")
    public void testLiteralComparisonOperations(Integer fieldValue, Integer ruleValue, TruthValue expectedResult) throws Exception {
        ValidationLogic<NullableFieldSubject> validationLogic = ensureThat(NullableFieldSubject.class)
                .field(FIELD)
                .isGreaterThan(ruleValue)
                .build();


        NullableFieldSubject subject = new NullableFieldSubject(fieldValue);

        Assertions.assertThat(validationLogic.verify(subject)).isEqualTo(expectedResult);
    }
}