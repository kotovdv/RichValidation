package com.jquartz.rich.validation.core.evaluation.exception;

import com.jquartz.rich.validation.core.exception.RichValidationException;
import com.jquartz.rich.validation.core.rule.ClassField;

/**
 * @author Dmitriy Kotov
 */
public class CannotDetermineTrustworthinessException extends RichValidationException {

    public CannotDetermineTrustworthinessException(ClassField<?, ?> classField) {
        super("No information about [" + classField + "] trustworthiness found");
    }
}
