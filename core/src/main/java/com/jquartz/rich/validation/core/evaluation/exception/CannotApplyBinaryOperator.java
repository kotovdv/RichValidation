package com.jquartz.rich.validation.core.evaluation.exception;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.TruthValueBinaryOperator;
import com.jquartz.rich.validation.core.exception.RichValidationException;

public class CannotApplyBinaryOperator extends RichValidationException {

    public CannotApplyBinaryOperator(TruthValue left, TruthValue right, TruthValueBinaryOperator operator) {
        super(String.format("Operator [%s] can not be applied to combination of [%s |%s]", operator, left, right));
    }
}