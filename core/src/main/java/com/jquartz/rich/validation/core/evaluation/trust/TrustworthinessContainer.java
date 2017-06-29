package com.jquartz.rich.validation.core.evaluation.trust;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.rule.ClassField;

import java.util.HashMap;
import java.util.Map;

import static com.jquartz.rich.validation.core.evaluation.TruthValue.TRUE;

/**
 * @author Dmitriy Kotov
 */
public class TrustworthinessContainer implements Trustworthiness {
    private final Map<ClassField<?, ?>, TruthValue> truthValueMap = new HashMap<>();

    public void associateValidationResult(ClassField<?, ?> targetField, TruthValue truthValue) {
        this.truthValueMap.put(targetField, truthValue);
    }

    @Override
    public boolean isTrustworthy(ClassField<?, ?> targetField) {
        return truthValueMap.getOrDefault(targetField, TRUE) == TRUE;
    }
}
