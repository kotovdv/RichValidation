package com.jquartz.rich.validation.core.expression.junction.builder;

import com.jquartz.rich.validation.core.exception.RichValidationException;

public class UnableToConstructEmptyJunctionException extends RichValidationException {

    public UnableToConstructEmptyJunctionException() {
        super("Unable to construct expression junction out of 0 expressions");
    }
}