package com.jquartz.rich.validation.core.subject;

/**
 * @author Dmitriy Kotov
 */
public class PersonSubject {
    public static final String AGE = "age";
    public static final String SEX = "sex";
    private final int age;
    private final Sex sex;

    public PersonSubject(int age, Sex sex) {
        this.age = age;
        this.sex = sex;
    }

    public enum Sex {
        MALE,
        FEMALE
    }
}
