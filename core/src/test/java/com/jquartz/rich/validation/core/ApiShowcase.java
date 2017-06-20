package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.subjects.VerificationSubject;
import org.junit.Test;

import static com.jquartz.rich.validation.core.verification.builder.VerificationLogicBuilder.ensureThat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitriy Kotov
 */
public class ApiShowcase {

    @Test
    public void simpleVerificationLogicTest() throws Exception {
        VerificationSubject subject = new VerificationSubject(15);

        VerificationLogic<VerificationSubject> logic = ensureThat(VerificationSubject.class)
                .field("firstField")
                .isGreaterThan(10L)
                .and()
                .isLessThan(20L)
                .create();

        assertThat(logic.verify(subject)).isTrue();
    }
}

