package com.jquartz.rich.validation.core.api.dsl.is;

import com.jquartz.rich.validation.core.api.dsl.conjunction.TargetConjunctionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class TargetIsPartBuilder<T> extends IsPartBuilder<T, TargetIsPartBuilder<T>, TargetConjunctionPartBuilder<T>> {

    public TargetIsPartBuilder(RuleLogicBuilder<T> builder) {
        super(builder.getTargetClass(), builder.getTargetFieldName(), builder);
    }

    @Override
    protected TargetConjunctionPartBuilder<T> createConjunctionBuilder() {
        return new TargetConjunctionPartBuilder<>(this, builder);
    }
}
