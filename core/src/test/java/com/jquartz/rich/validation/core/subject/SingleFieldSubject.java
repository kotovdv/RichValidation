package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class SingleFieldSubject {

    private final long field;

    public SingleFieldSubject(long field) {
        this.field = field;
    }

    public static String getFieldName() {
        return "field";
    }
}