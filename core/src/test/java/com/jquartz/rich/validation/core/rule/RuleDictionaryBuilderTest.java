package com.jquartz.rich.validation.core.rule;

import com.jquartz.rich.validation.core.Rule;
import com.jquartz.rich.validation.core.subject.TwoFieldsSubject;
import org.junit.Test;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;

public class RuleDictionaryBuilderTest {


    @Test
    public void test() throws Exception {
        Rule<TwoFieldsSubject> firstFieldRule = ensureThat(TwoFieldsSubject.class)
                .field(TwoFieldsSubject.FIRST_FIELD)
                .isGreaterThanField(TwoFieldsSubject.SECOND_FIELD)
                .build();

        Rule<TwoFieldsSubject> secondFieldRule = ensureThat(TwoFieldsSubject.class)
                .field(TwoFieldsSubject.SECOND_FIELD)
                .isGreaterThan(0)
                .build();

        RuleDictionaryBuilder ruleDictionaryBuilder = new RuleDictionaryBuilder();

        RuleDictionary dictionary = ruleDictionaryBuilder
                .submit(firstFieldRule)
                .submit(secondFieldRule)
                .build();

        dictionary.validate(new TwoFieldsSubject(100, -10));

        System.out.println(dictionary);
    }


    @Test
    public void testCycledCase() throws Exception {
/*        Rule<FloatingPointIntegrationTestSubject> firstFieldRule = ensureThat(
                FloatingPointIntegrationTestSubject.class)
                .field(FloatingPointIntegrationTestSubject.FIRST_FIELD)
                .isGreaterThanField(FloatingPointIntegrationTestSubject.SECOND_FIELD)
                .build();

        Rule<FloatingPointIntegrationTestSubject> secondFieldRule = ensureThat(
                FloatingPointIntegrationTestSubject.class)
                .field(FloatingPointIntegrationTestSubject.SECOND_FIELD)
                .isGreaterThanField(FloatingPointIntegrationTestSubject.THIRD_FIELD)
                .build();

        Rule<FloatingPointIntegrationTestSubject> thirdFieldRule = ensureThat(
                FloatingPointIntegrationTestSubject.class)
                .field(FloatingPointIntegrationTestSubject.THIRD_FIELD)
                .isLessThanField(FloatingPointIntegrationTestSubject.SECOND_FIELD)
                .build();

        RuleDictionaryBuilder ruleDictionaryBuilder = new RuleDictionaryBuilder();

        RuleDictionary dictionary = ruleDictionaryBuilder
                .submit(firstFieldRule)
                .submit(secondFieldRule)
                .submit(thirdFieldRule)
                .build();

        System.out.println(dictionary);*/


    }
}