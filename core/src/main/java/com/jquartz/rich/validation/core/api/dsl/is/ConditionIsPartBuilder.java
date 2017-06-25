package com.jquartz.rich.validation.core.api.dsl.is;

import com.jquartz.rich.validation.core.api.dsl.conjunction.ConditionConjunctionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class ConditionIsPartBuilder<T> extends IsPartBuilder<T, ConditionIsPartBuilder<T>, ConditionConjunctionPartBuilder<T>> {
    public ConditionIsPartBuilder(Class<T> targetClass, String fieldName, RuleLogicBuilder<T> builder) {
        super(targetClass, fieldName, builder);
    }

    @Override
    protected ConditionConjunctionPartBuilder<T> createConjunctionBuilder() {
        return new ConditionConjunctionPartBuilder<>(this, builder);
    }

    public ConditionIsPartBuilder<T> field(String fieldName) {
        return new ConditionIsPartBuilder<>(getTargetClass(), fieldName, builder);
    }
}
