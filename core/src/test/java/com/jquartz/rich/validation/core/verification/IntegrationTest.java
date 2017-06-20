package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.subjects.SingleFieldSubject;
import com.jquartz.rich.validation.core.verification.subjects.TwoFieldsSubject;
import org.junit.Test;

import static com.jquartz.rich.validation.core.verification.builder.VerificationLogicBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
public class IntegrationTest {

    private static VerificationLogic<SingleFieldSubject> fieldToLiteralComparison = ensureThat(SingleFieldSubject.class)
            .field("firstField")
            .isGreaterThan(10)
            .create();
    private static VerificationLogic<TwoFieldsSubject> fieldToFieldComparison = ensureThat(TwoFieldsSubject.class)
            .field("firstField")
            .isGreaterThanField("secondField")
            .create();

    @Test
    public void testPositiveLiteralComparison() throws Exception {
        SingleFieldSubject subject = new SingleFieldSubject(15);

        assertThat(fieldToLiteralComparison.verify(subject)).isTrue();
    }

    @Test
    public void testNegativeLiteralComparison() throws Exception {
        SingleFieldSubject subject = new SingleFieldSubject(5);

        assertThat(fieldToLiteralComparison.verify(subject)).isFalse();
    }

    @Test
    public void testPositiveFieldToFieldComparison() throws Exception {
        TwoFieldsSubject subject = new TwoFieldsSubject(15, 10);

        assertThat(fieldToFieldComparison.verify(subject)).isTrue();
    }

    @Test
    public void testNegativeFieldToFieldComparison() throws Exception {
        TwoFieldsSubject subject = new TwoFieldsSubject(10, 15);

        assertThat(fieldToFieldComparison.verify(subject)).isFalse();
    }
}