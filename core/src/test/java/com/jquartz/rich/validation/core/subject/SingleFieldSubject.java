package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class SingleFieldSubject {

    private final long firstField;

    public SingleFieldSubject(long firstField) {
        this.firstField = firstField;
    }

    public static String getFieldName() {
        return "firstField";
    }
}