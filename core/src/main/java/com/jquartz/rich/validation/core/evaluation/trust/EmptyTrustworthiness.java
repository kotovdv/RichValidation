package com.jquartz.rich.validation.core.evaluation.trust;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;

/**
 * @author Dmitriy Kotov
 */
public class EmptyTrustworthiness extends Trustworthiness {

    public static Trustworthiness INSTANCE = new EmptyTrustworthiness();

    @Override
    public void associateValidationResult(ClassField<?, ?> targetField, TruthValue truthValue) {
    }

    @Override
    public boolean isTrustworthy(ClassField<?, ?> targetField) {
        return true;
    }
}
