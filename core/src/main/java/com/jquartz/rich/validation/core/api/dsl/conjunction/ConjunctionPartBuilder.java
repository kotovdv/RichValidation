package com.jquartz.rich.validation.core.api.dsl.conjunction;

import com.jquartz.rich.validation.core.rule.Rule;
import com.jquartz.rich.validation.core.api.dsl.is.IsPartBuilder;
import com.jquartz.rich.validation.core.api.dsl.logic.RuleLogicBuilder;

/**
 * @param <T>  Source class type
 * @param <IB> Is part builder type
 * @param <CB> Conjunction part builder type
 * @author Dmitriy Kotov
 */
public abstract class ConjunctionPartBuilder<T, IB extends IsPartBuilder<T, IB, CB>, CB extends ConjunctionPartBuilder<T, IB, CB>> {

    protected final IB isPartBuilder;
    protected final RuleLogicBuilder<T> builder;

    public ConjunctionPartBuilder(IB isPartBuilder,
                                  RuleLogicBuilder<T> logicBuilder) {
        this.isPartBuilder = isPartBuilder;
        this.builder = logicBuilder;
    }

    public IB and() {
        return isPartBuilder;
    }

    public IB or() {
        builder.startNewOrJunction();

        return isPartBuilder;
    }

    public Rule<T> build() {
        builder.persistCurrentChanges();

        return builder.build();
    }
}