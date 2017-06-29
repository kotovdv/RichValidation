package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class ThreeFieldsSubject {
    public static final String FIRST_FIELD = "firstField";
    public static final String SECOND_FIELD = "secondField";
    public static final String THIRD_FIELD = "thirdField";

    private final Integer firstField;
    private final Integer secondField;
    private final Integer thirdField;

    public ThreeFieldsSubject(Integer firstField, Integer secondField, Integer thirdField) {
        this.firstField = firstField;
        this.secondField = secondField;
        this.thirdField = thirdField;
    }
}
