package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.subject.FloatingPointIntegrationTestSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
import static com.jquartz.rich.validation.core.subject.FloatingPointIntegrationTestSubject.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class FloatingPointComparisonIntegrationTest {

    @DataProvider
    public static Object[][] floatingPointScenarios() {
        Rule<FloatingPointIntegrationTestSubject> firstFieldRule =
                ensureThat(FloatingPointIntegrationTestSubject.class)
                        .field(FIRST_FIELD)
                        .isGreaterThanField(SECOND_FIELD)
                        .and()
                        .isGreaterThanField(THIRD_FIELD)
                        .build();

        Rule<FloatingPointIntegrationTestSubject> secondFieldRule =
                ensureThat(FloatingPointIntegrationTestSubject.class)
                        .field(SECOND_FIELD)
                        .isGreaterThanField(THIRD_FIELD)
                        .and()
                        .isLessThanField(FIRST_FIELD)
                        .build();

        Rule<FloatingPointIntegrationTestSubject> thirdFieldRule =
                ensureThat(FloatingPointIntegrationTestSubject.class)
                        .field(THIRD_FIELD)
                        .isLessThanField(SECOND_FIELD)
                        .and()
                        .isLessThanField(FIRST_FIELD)
                        .build();

        List<Rule<FloatingPointIntegrationTestSubject>> rules = Arrays.asList(firstFieldRule, secondFieldRule, thirdFieldRule);
        return new Object[][]{
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("0.1"), 0.01D, 0.001F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("0.333"), 0.33, 0.3F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("10.5"), 10.4, 10.3F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("10.5"), 10.49D, 10.48F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("10.5"), 10.499D, 10.498F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("10.5"), 10.4999D, 10.4998F)},
                {rules, new FloatingPointIntegrationTestSubject(new BigDecimal("10.5"), 10.49999D, 10.49998F)},
        };
    }


    @Test
    @UseDataProvider("floatingPointScenarios")
    public void testFloatingPointComparisons(Collection<Rule<FloatingPointIntegrationTestSubject>> rules, FloatingPointIntegrationTestSubject subject) throws Exception {
        for (Rule<FloatingPointIntegrationTestSubject> rule : rules) {
            assertThat(rule.validate(subject)).isEqualTo(TruthValue.TRUE);
        }
    }
}
