package com.jquartz.rich.validation.core.evaluation.trust;

import com.jquartz.rich.validation.core.rule.ClassField;

/**
 * @author Dmitriy Kotov
 */
public interface Trustworthiness {
    boolean isTrustworthy(ClassField<?, ?> targetField);
}
