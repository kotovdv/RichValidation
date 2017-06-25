package com.jquartz.rich.validation.core.api.dsl.is;

import com.jquartz.rich.validation.core.api.dsl.conjunction.OtherwiseConjunctionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class OtherwiseIsPartBuilder<T> extends IsPartBuilder<T, OtherwiseIsPartBuilder<T>, OtherwiseConjunctionPartBuilder<T>> {

    public OtherwiseIsPartBuilder(RuleLogicBuilder<T> builder) {
        super(builder.getTargetClass(), builder.getTargetFieldName(), builder);
    }

    @Override
    protected OtherwiseConjunctionPartBuilder<T> createConjunctionBuilder() {
        return new OtherwiseConjunctionPartBuilder<>(this, builder);
    }
}