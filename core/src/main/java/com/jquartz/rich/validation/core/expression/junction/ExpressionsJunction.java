package com.jquartz.rich.validation.core.expression.junction;

public interface ExpressionsJunction<T> {

    boolean verify(T instance);
}