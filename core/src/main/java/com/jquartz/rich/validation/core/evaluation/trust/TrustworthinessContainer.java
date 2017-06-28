package com.jquartz.rich.validation.core.evaluation.trust;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitriy Kotov
 */
public class TrustworthinessContainer implements Trustworthiness {
    //RULES SHOULD ONLY READ THIS STRUCTURE
    //DTS CAN ALSO MODIFY
    private final Map<ClassField<?, ?>, TruthValue> truthValueMap = new HashMap<>();

    public void associateValidationResult(ClassField<?, ?> targetField, TruthValue truthValue) {
        this.truthValueMap.put(targetField, truthValue);
    }

    @Override
    public boolean isNotTrustworthy(ClassField<?, ?> target) {
        return !isTrustworthy(target);
    }

    @Override
    public boolean isTrustworthy(ClassField<?, ?> targetField) {
        TruthValue truthValue = truthValueMap.get(targetField);
        /*if (truthValue == null) {
            throw new CannotDetermineTrustworthinessException(targetField);
        }*/

        return truthValue == null || truthValue == TruthValue.TRUE;
    }

}
