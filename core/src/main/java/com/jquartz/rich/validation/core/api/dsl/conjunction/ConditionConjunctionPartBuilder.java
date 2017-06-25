package com.jquartz.rich.validation.core.api.dsl.conjunction;

import com.jquartz.rich.validation.core.api.dsl.is.ConditionIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.is.OtherwiseIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.is.TargetIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class ConditionConjunctionPartBuilder<T> extends ConjunctionPartBuilder<T, ConditionIsPartBuilder<T>, ConditionConjunctionPartBuilder<T>> {

    public ConditionConjunctionPartBuilder(ConditionIsPartBuilder<T> targetPart, RuleLogicBuilder<T> builder) {
        super(targetPart, builder);
    }

    public TargetIsPartBuilder<T> orElse() {
        builder.persistCurrentChanges();
        builder.switchToMustPart();

        return new TargetIsPartBuilder<>(builder);
    }

    public OtherwiseIsPartBuilder<T> otherwise() {
        builder.persistCurrentChanges();
        builder.switchToOtherwise();

        return new OtherwiseIsPartBuilder<>(builder);
    }
}
