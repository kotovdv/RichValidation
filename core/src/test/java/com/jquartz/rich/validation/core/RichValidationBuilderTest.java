package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.rule.exception.CircularDependencyFoundException;
import com.jquartz.rich.validation.core.subject.FloatingPointIntegrationTestSubject;
import org.junit.Test;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;

/**
 *
 * @author Dmitriy Kotov
 */
public class RichValidationBuilderTest {

    @Test(expected = CircularDependencyFoundException.class)
    public void testSimpleCycledCase() throws Exception {
        Rule<FloatingPointIntegrationTestSubject> firstFieldRule = ensureThat(
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
                .isLessThanField(FloatingPointIntegrationTestSubject.FIRST_FIELD)
                .build();

        RichValidationBuilder ruleDictionaryBuilder = new RichValidationBuilder();

        ruleDictionaryBuilder
                .submit(firstFieldRule)
                .submit(secondFieldRule)
                .submit(thirdFieldRule)
                .build();
    }

}