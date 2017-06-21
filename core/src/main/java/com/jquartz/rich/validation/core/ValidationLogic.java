package com.jquartz.rich.validation.core;

import com.jquartz.rich.validation.core.expression.junction.ExpressionsJunction;

/**
 * @author Dmitriy Kotov
 */
public class ValidationLogic<T> {

    private final ExpressionsJunction<T> junction;

    public ValidationLogic(ExpressionsJunction<T> junction) {
        this.junction = junction;
    }

    public boolean verify(T instance) {
        return junction.verify(instance);
    }
}