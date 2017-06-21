package com.jquartz.rich.validation.core.verification;

import com.jquartz.rich.validation.core.verification.expression.junction.ExpressionsJunction;

/**
 * @author Dmitriy Kotov
 */
public class VerificationLogic<T> {

    private final ExpressionsJunction<T> junction;

    public VerificationLogic(ExpressionsJunction<T> junction) {
        this.junction = junction;
    }

    public boolean verify(T instance) {
        return junction.verify(instance);
    }
}