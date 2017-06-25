package com.jquartz.rich.validation.core.api.dsl.logic;

import com.jquartz.rich.validation.core.exception.RichValidationException;

/**
 * @author Dmitriy Kotov
 */
public class IllegalPointerException extends RichValidationException {
    public IllegalPointerException(Pointer pointer) {
        super(String.format("Pointer [%s] is not supported", pointer));
    }
}
