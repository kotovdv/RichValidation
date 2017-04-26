package com.kotovdv.rich.validation.core.verification;

import com.kotovdv.rich.validation.core.annotations.Verify;

/**
 * @author Dmitriy Kotov
 */
@Verify("firstField > secondField")
@Verify("secondField > thirdField")
public class VerificationSubject {

    private final long firstField;
    private final long secondField;
    private final long thirdField;

    public VerificationSubject(long firstField, long secondField, long thirdField) {
        this.firstField = firstField;
        this.secondField = secondField;
        this.thirdField = thirdField;
    }
}