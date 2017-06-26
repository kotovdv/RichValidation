package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator;
import com.jquartz.rich.validation.core.subject.ComparisonResultSubject;
import com.jquartz.rich.validation.core.subject.PersonSubject;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jquartz.rich.validation.core.api.dsl.RuleBuilder.ensureThat;
import static com.jquartz.rich.validation.core.expression.comparison.operator.ComparisonOperator.*;
import static com.jquartz.rich.validation.core.subject.ComparisonResultSubject.COMPARISON_RESULT;
import static com.jquartz.rich.validation.core.subject.ComparisonResultSubject.OPERATOR;
import static com.jquartz.rich.validation.core.subject.PersonSubject.AGE;
import static com.jquartz.rich.validation.core.subject.PersonSubject.SEX;
import static com.jquartz.rich.validation.core.subject.PersonSubject.Sex.FEMALE;
import static com.jquartz.rich.validation.core.subject.PersonSubject.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
@RunWith(DataProviderRunner.class)
public class ConditionalExpressionIntegrationTest {

    @DataProvider
    public static Object[][] firstPersonRuleValidation() {
        return new Object[][]{
                {new PersonSubject(20, FEMALE), TruthValue.TRUE},
                {new PersonSubject(19, FEMALE), TruthValue.FALSE},
                {new PersonSubject(19, MALE), TruthValue.TRUE},
                {new PersonSubject(15, MALE), TruthValue.TRUE},
                {new PersonSubject(19, null), TruthValue.TRUE},
                {new PersonSubject(21, null), TruthValue.TRUE},
        };
    }

    @DataProvider
    public static Object[][] secondPersonRuleValidation() {
        return new Object[][]{
                {new PersonSubject(20, FEMALE), TruthValue.TRUE},
                {new PersonSubject(19, FEMALE), TruthValue.FALSE},
                {new PersonSubject(19, MALE), TruthValue.TRUE},
                {new PersonSubject(15, MALE), TruthValue.FALSE},
                {new PersonSubject(19, null), TruthValue.TRUE},
                {new PersonSubject(21, null), TruthValue.TRUE},
        };
    }

    @DataProvider
    public static Object[][] comparisonSubjectRuleValidation() {
        return new Object[][]{
                {new ComparisonResultSubject(0, ComparisonOperator.EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(1, ComparisonOperator.EQUAL_TO), TruthValue.FALSE},
                {new ComparisonResultSubject(-1, ComparisonOperator.EQUAL_TO), TruthValue.FALSE},

                {new ComparisonResultSubject(1, ComparisonOperator.GREATER_THAN), TruthValue.TRUE},
                {new ComparisonResultSubject(0, ComparisonOperator.GREATER_THAN), TruthValue.FALSE},
                {new ComparisonResultSubject(-1, ComparisonOperator.GREATER_THAN), TruthValue.FALSE},

                {new ComparisonResultSubject(-1, ComparisonOperator.LESS_THAN), TruthValue.TRUE},
                {new ComparisonResultSubject(0, ComparisonOperator.LESS_THAN), TruthValue.FALSE},
                {new ComparisonResultSubject(1, ComparisonOperator.LESS_THAN), TruthValue.FALSE},

                {new ComparisonResultSubject(1, ComparisonOperator.NOT_EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(0, ComparisonOperator.NOT_EQUAL_TO), TruthValue.FALSE},
                {new ComparisonResultSubject(-1, ComparisonOperator.NOT_EQUAL_TO), TruthValue.TRUE},

                {new ComparisonResultSubject(1, ComparisonOperator.GREATER_OR_EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(0, ComparisonOperator.GREATER_OR_EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(-1, ComparisonOperator.GREATER_OR_EQUAL_TO), TruthValue.TRUE},

                {new ComparisonResultSubject(1, ComparisonOperator.LESS_OR_EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(0, ComparisonOperator.LESS_OR_EQUAL_TO), TruthValue.TRUE},
                {new ComparisonResultSubject(-1, ComparisonOperator.LESS_OR_EQUAL_TO), TruthValue.TRUE},


        };
    }

    /**
     * AGE MUST BE >= 20 WHEN SEX = FEMALE
     * OTHERWISE
     * NO REQUIREMENTS
     */
    @Test
    @UseDataProvider("firstPersonRuleValidation")
    public void testFirstPersonRuleValidation(PersonSubject subject, TruthValue expectedResult) throws Exception {
        Rule<PersonSubject> rule = ensureThat(PersonSubject.class)
                .field(AGE)
                .isGreaterOrCoequalTo(20)
                .when()
                .field(SEX).isEqualTo(FEMALE)
                .build();

        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }

    /**
     * AGE  MUST BE >= 20 WHEN SEX = FEMALE
     * OTHERWISE
     * MUST HAVE AGE >= 16
     */
    @Test
    @UseDataProvider("secondPersonRuleValidation")
    public void testSecondPersonRuleValidation(PersonSubject subject, TruthValue expectedResult) throws Exception {
        Rule<PersonSubject> rule = ensureThat(PersonSubject.class)
                .field(AGE)
                .isGreaterOrCoequalTo(20)
                .when().field(SEX).isEqualTo(FEMALE)
                .otherwise()
                .isGreaterOrCoequalTo(16)
                .build();

        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }

    /**
     * COMPARISON RESULT MUST BE < 0 WHEN OPERATOR = LESS THAN
     * ELSE
     * COMPARISON RESULT MUST BE > 0 WHEN OPERATOR = GREATER THAN
     * ELSE
     * COMPARISON RESULT MUST BE = 0 WHEN OPERATOR = EQUAL TO
     * ELSE
     * COMPARISON RESULT MUST BE != 0 WHEN OPERATOR = NOT EQUAL TO
     * OTHERWISE
     * NO REQUIREMENTS
     */
    @Test
    @UseDataProvider("comparisonSubjectRuleValidation")
    public void testComparisonSubjectRuleValidation(ComparisonResultSubject subject, TruthValue expectedResult) throws Exception {
        Rule<ComparisonResultSubject> rule = ensureThat(ComparisonResultSubject.class)
                .field(COMPARISON_RESULT)
                .isLessThan(0)
                .when()
                .field(OPERATOR).isEqualTo(LESS_THAN)
                .orElse()
                .isGreaterThan(0)
                .when()
                .field(OPERATOR).isEqualTo(GREATER_THAN)
                .orElse()
                .isEqualTo(0)
                .when()
                .field(OPERATOR).isEqualTo(EQUAL_TO)
                .orElse()
                .isNotEqualTo(0)
                .when()
                .field(OPERATOR).isEqualTo(NOT_EQUAL_TO)
                .build();

        assertThat(rule.validate(subject)).isEqualTo(expectedResult);
    }
}
