package com.jquartz.rich.validation.core.api.dsl.conjunction;

import com.jquartz.rich.validation.core.api.dsl.is.OtherwiseIsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @author Dmitriy Kotov
 */
public class OtherwiseConjunctionPartBuilder<T> extends ConjunctionPartBuilder<T, OtherwiseIsPartBuilder<T>, OtherwiseConjunctionPartBuilder<T>> {

    public OtherwiseConjunctionPartBuilder(OtherwiseIsPartBuilder<T> isPartBuilder, RuleLogicBuilder<T> logicBuilder) {
        super(isPartBuilder, logicBuilder);
    }
}
