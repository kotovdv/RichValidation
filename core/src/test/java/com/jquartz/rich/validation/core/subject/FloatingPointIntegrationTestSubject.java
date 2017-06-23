package com.jquartz.rich.validation.core.subject;

import java.math.BigDecimal;

public class FloatingPointIntegrationTestSubject {
    public static final String FIRST_FIELD = "firstField";
    public static final String SECOND_FIELD = "secondField";
    public static final String THIRD_FIELD = "thirdField";

    private final BigDecimal firstField;
    private final double secondField;
    private final float thirdField;

    public FloatingPointIntegrationTestSubject(BigDecimal firstField, double secondField, float thirdField) {
        this.firstField = firstField;
        this.secondField = secondField;
        this.thirdField = thirdField;
    }

    @Override
    public String toString() {
        return "FloatingPointIntegrationTestSubject{" +
                "firstField=" + firstField +
                ", secondField=" + secondField +
                ", thirdField=" + thirdField +
                '}';
    }
}