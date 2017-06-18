package com.jquartz.rich.validation.core.verification.expression.factory.handler;

import com.jquartz.rich.validation.core.annotations.Validate;
import com.jquartz.rich.validation.core.verification.expression.value.Value;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public class FieldHandler implements Handler {

    @Override
    public boolean canHandle(Validate verify) {
        return false;
    }

    @Override
    public Value doHandle(Field field, Validate verify) {
        return null;
    }
}
