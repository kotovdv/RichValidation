package com.jquartz.rich.validation.core.verification.subjects;

/**
 * @author Dmitriy Kotov
 */
public class TwoFieldsSubject {

    private final int firstField;
    private final int secondField;

    public TwoFieldsSubject(int firstField, int secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }

    public static String getFirstFieldName() {
        return "firstField";
    }

    public static String getSecondFieldName() {
        return "secondField";
    }
}