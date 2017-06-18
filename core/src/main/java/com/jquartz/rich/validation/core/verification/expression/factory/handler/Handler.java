package com.jquartz.rich.validation.core.verification.expression.factory.handler;

import com.jquartz.rich.validation.core.annotations.Validate;
import com.jquartz.rich.validation.core.verification.expression.value.Value;

import java.lang.reflect.Field;

/**
 * @author Dmitriy Kotov
 */
public interface Handler {

    boolean canHandle(Validate verify);

    Value doHandle(Field field, Validate verify);
}
