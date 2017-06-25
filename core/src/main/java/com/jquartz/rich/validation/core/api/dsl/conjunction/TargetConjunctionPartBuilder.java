package com.jquartz.rich.validation.core.api.dsl.conjunction;

import com.jquartz.rich.validation.core.api.dsl.ConditionPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.is.TargetIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class TargetConjunctionPartBuilder<T> extends ConjunctionPartBuilder<T, TargetIsPartBuilder<T>, TargetConjunctionPartBuilder<T>> {

    public TargetConjunctionPartBuilder(TargetIsPartBuilder<T> targetPart, RuleLogicBuilder<T> logicBuilder) {
        super(targetPart, logicBuilder);
    }

    public ConditionPartBuilder<T> when() {
        builder.switchToConditionPart();

        return new ConditionPartBuilder<>(isPartBuilder.getTargetClass(), builder);
    }
}
