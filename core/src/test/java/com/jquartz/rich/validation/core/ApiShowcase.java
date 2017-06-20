package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.builder.MustPartBuilder;
import com.jquartz.rich.validation.core.verification.subjects.VerificationSubject;
import org.junit.Test;

import static com.jquartz.rich.validation.core.verification.builder.VerificationLogicBuilder.ensureThat;

/**
 * @author Dmitriy Kotov
 */
public class ApiShowcase {

    @Test
    public void name() throws Exception {
        MustPartBuilder<VerificationSubject> builder = ensureThat(VerificationSubject.class)
                .field("fieldOne")
                .isGreaterThan(10)
                .and()
                .isGreaterThan(5)
                .and()
                .isGreaterThanField("fieldTwo");


        VerificationLogic<VerificationSubject> logic = builder
                .create();

        VerificationSubject subject = new VerificationSubject(15, 10, 5);
    }
}