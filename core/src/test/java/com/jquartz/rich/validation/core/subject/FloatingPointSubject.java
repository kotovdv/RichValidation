package com.jquartz.rich.validation.core.subject;

public class FloatingPointSubject {

    private final double field;

    public FloatingPointSubject(double field) {
        this.field = field;
    }

    public static String getFieldName() {
        return "field";
    }
}