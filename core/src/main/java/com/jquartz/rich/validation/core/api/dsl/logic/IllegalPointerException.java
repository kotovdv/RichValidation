package com.jquartz.rich.validation.core.api.dsl.logic;

import com.jquartz.rich.validation.core.exception.RichValidationException;

/**
 * @author Dmitriy Kotov
 */
public class IllegalPointerException extends RichValidationException {
    public IllegalPointerException(RulePartPointer pointer) {
        super(String.format("RulePartPointer [%s] is not supported", pointer));
    }
}
