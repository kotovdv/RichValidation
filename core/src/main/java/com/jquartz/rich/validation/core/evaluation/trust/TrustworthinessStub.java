package com.jquartz.rich.validation.core.evaluation.trust;

import com.jquartz.rich.validation.core.rule.ClassField;

/**
 * @author Dmitriy Kotov
 */
public class TrustworthinessStub implements Trustworthiness {

    public static Trustworthiness INSTANCE = new TrustworthinessStub();

    @Override
    public boolean isTrustworthy(ClassField<?, ?> targetField) {
        return true;
    }
}
