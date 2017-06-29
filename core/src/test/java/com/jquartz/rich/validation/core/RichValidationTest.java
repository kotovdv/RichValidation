package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;
import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.rule.evaluation.ValidationResult;
import com.jquartz.rich.validation.core.subject.TwoFieldsSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class RichValidationTest {

    /**
     * Demonstrates simple scenario with the following dependency tree
     * Root node = Field = TwoFieldsSubject.firstField | Rule = TwoFieldsSubject.firstField > TwoFieldsSubject.secondField
     * Child node = Field = TwoFieldsSubject.secondField | Rule = TwoFieldsSubject.secondField > 0
     */
    @DataProvider
    public static Object[][] firstScenario() {
        ClassField<Integer, TwoFieldsSubject> firstField = new ClassField<>(TwoFieldsSubject.class, Integer.class, TwoFieldsSubject.FIRST_FIELD);
        ClassField<Integer, TwoFieldsSubject> secondField = new ClassField<>(TwoFieldsSubject.class, Integer.class, TwoFieldsSubject.SECOND_FIELD);

        Rule<TwoFieldsSubject> firstFieldRule = ensureThat(TwoFieldsSubject.class)
                .field(TwoFieldsSubject.FIRST_FIELD)
                .isGreaterThanField(TwoFieldsSubject.SECOND_FIELD)
                .build();

        Rule<TwoFieldsSubject> secondFieldRule = ensureThat(TwoFieldsSubject.class)
                .field(TwoFieldsSubject.SECOND_FIELD)
                .isGreaterThan(0)
                .build();

        RichValidationBuilder richValidationBuilder = RichValidation.builder();

        RichValidation richValidation = richValidationBuilder
                .submit(firstFieldRule)
                .submit(secondFieldRule)
                .build();

        return new Object[][]{
                {richValidation, new TwoFieldsSubject(100, 70), of(
                        TruthValue.TRUE, asList(firstField, secondField))
                },
                {richValidation, new TwoFieldsSubject(60, 70), of(
                        TruthValue.FALSE, singletonList(firstField),
                        TruthValue.TRUE, singletonList(secondField))
                },
                {richValidation, new TwoFieldsSubject(60, 0), of(
                        TruthValue.UNKNOWN, singletonList(firstField),
                        TruthValue.FALSE, singletonList(secondField))
                },
                {richValidation, new TwoFieldsSubject(null, 10), of(
                        TruthValue.UNKNOWN, singletonList(firstField),
                        TruthValue.TRUE, singletonList(secondField))
                },
                {richValidation, new TwoFieldsSubject(null, null), of(
                        TruthValue.UNKNOWN, asList(firstField, secondField))
                }
        };
    }

    @Test
    @UseDataProvider("firstScenario")
    public void testFirstScenario(RichValidation richValidation, TwoFieldsSubject subject, Map<TruthValue, Collection<ClassField<?, ?>>> results) throws Exception {
        assertValidation(richValidation.validateSilently(subject), results);
    }

        /*@DataProvider
    public static Object[][] secondScenario() {
        ClassField<Integer, ThreeFieldsSubject> firstField = new ClassField<>(ThreeFieldsSubject.class, Integer.class, ThreeFieldsSubject.FIRST_FIELD);
        ClassField<Integer, ThreeFieldsSubject> secondField = new ClassField<>(ThreeFieldsSubject.class, Integer.class, ThreeFieldsSubject.SECOND_FIELD);
        ClassField<Integer, ThreeFieldsSubject> thirdField = new ClassField<>(ThreeFieldsSubject.class, Integer.class, ThreeFieldsSubject.THIRD_FIELD);

        Rule<ThreeFieldsSubject> firstFieldRule = ensureThat(ThreeFieldsSubject.class)
                .field(ThreeFieldsSubject.FIRST_FIELD)
                .isGreaterThanField(ThreeFieldsSubject.SECOND_FIELD)
                .build();

        Rule<ThreeFieldsSubject> secondFieldRule = ensureThat(ThreeFieldsSubject.class)
                .field(ThreeFieldsSubject.SECOND_FIELD)
                .isGreaterThanField(ThreeFieldsSubject.SECOND_FIELD)
                .build();

        Rule<ThreeFieldsSubject> thirdFieldRule = ensureThat(ThreeFieldsSubject.class)
                .field(ThreeFieldsSubject.THIRD_FIELD)
                .isGreaterThanField(ThreeFieldsSubject.FIRST_FIELD)
                .build();

        RichValidationBuilder ruleDictionaryBuilder = RichValidation.builder();

        RichValidation dictionary = ruleDictionaryBuilder
                .submit(firstFieldRule)
                .submit(secondFieldRule)
                .submit(thirdFieldRule)
                .build();

        return new Object[][]{
                {dictionary, new TwoFieldsSubject(100, 70), of(
                        TruthValue.TRUE, asList(firstField, secondField))
                },
        };
    }*/



    private void assertValidation(ValidationResult validationResult, Map<TruthValue, Collection<ClassField<?, ?>>> results) {
        for (Map.Entry<TruthValue, Collection<ClassField<?, ?>>> entry : results.entrySet()) {
            Collection<ClassField<?, ?>> actualResults = validationResult.getFieldsWithResult(entry.getKey());

            Collection<ClassField<?, ?>> expectedResults = entry.getValue();
            assertThat(actualResults).containsAll(expectedResults);
        }
    }
}