package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.verification.VerificationLogic;
import com.jquartz.rich.validation.core.verification.VerificationLogicFactory;
import com.jquartz.rich.validation.core.verification.VerificationResult;
import com.jquartz.rich.validation.core.verification.VerificationSubject;
import org.junit.Test;

/**
 * @author Dmitriy Kotov
 */
public class ApiShowcase {

    @Test
    public void name() throws Exception {
        VerificationLogicFactory logicFactory = new VerificationLogicFactory();

        VerificationLogic logic = logicFactory.createFor(VerificationSubject.class);

        VerificationSubject subject = new VerificationSubject(20, 15, 10);

        VerificationResult result = logic.apply(subject);
    }
}