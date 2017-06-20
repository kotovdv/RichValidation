package com.jquartz.rich.validation.core.verification.subjects;

/**
 * @author Dmitriy Kotov
 */
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