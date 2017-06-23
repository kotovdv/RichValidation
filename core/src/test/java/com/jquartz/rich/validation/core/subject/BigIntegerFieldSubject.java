package com.jquartz.rich.validation.core.subject;

import java.math.BigInteger;

public class BigIntegerFieldSubject {
    public static final String FIELD = "field";
    private final BigInteger field;

    public BigIntegerFieldSubject(BigInteger field) {
        this.field = field;
    }
}