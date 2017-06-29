package com.jquartz.rich.validation.core.rule.exception;

import com.jquartz.rich.validation.core.exception.RichValidationException;
import com.jquartz.rich.validation.core.rule.evaluation.ValidationResult;

/**
 * @author Dmitriy Kotov
 */
public class ValidationException extends RichValidationException {

    private final ValidationResult results;

    public ValidationException(ValidationResult results) {
        super("Validation failed. " + parseResults(results));
        this.results = results;
    }

    public ValidationResult getResults() {
        return results;
    }

    private static String parseResults(ValidationResult results) {
        return results.toString();
    }
}
