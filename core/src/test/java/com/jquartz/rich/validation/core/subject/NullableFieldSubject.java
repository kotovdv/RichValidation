package com.jquartz.rich.validation.core.subject;

public class NullableFieldSubject {

    private final Integer field;

    public NullableFieldSubject(Integer field) {
        this.field = field;
    }

    public static String getFieldName() {
        return "field";
    }
}