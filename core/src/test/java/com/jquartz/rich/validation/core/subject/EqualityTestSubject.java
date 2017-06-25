package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class EqualityTestSubject {
    public static final String FIRST_FIELD = "firstField";
    public static final String SECOND_FIELD = "secondField";
    private final Object firstField;
    private final Object secondField;

    public EqualityTestSubject(Object firstField, Object secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }

    public Object getFirstField() {
        return firstField;
    }

    public Object getSecondField() {
        return secondField;
    }
}