package com.jquartz.rich.validation.core.verification.expression.junction;

public interface ExpressionsJunction<T> {

    boolean verify(T instance);
}