package com.jquartz.rich.validation.core.api.dsl;

import com.jquartz.rich.validation.core.api.dsl.is.ConditionIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class ConditionPartBuilder<T> {
    private final Class<T> targetClass;
    private final RuleLogicBuilder<T> builder;

    public ConditionPartBuilder(Class<T> targetClass, RuleLogicBuilder<T> builder) {
        this.targetClass = targetClass;
        this.builder = builder;
    }

    public ConditionIsPartBuilder<T> field(String anotherField) {
        return new ConditionIsPartBuilder<>(targetClass, anotherField, builder);
    }
}
