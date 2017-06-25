package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class TwoFieldsSubject {

    public static final String FIRST_FIELD = "firstField";
    public static final String SECOND_FIELD = "secondField";
    private final int firstField;
    private final int secondField;

    public TwoFieldsSubject(int firstField, int secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }
}