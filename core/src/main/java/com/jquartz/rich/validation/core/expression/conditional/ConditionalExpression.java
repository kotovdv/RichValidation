package com.jquartz.rich.validation.core.expression.conditional;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.expression.Expression;

import javax.annotation.Nonnull;

/**
 * @author Dmitriy Kotov
 */
public class ConditionalExpression<T> {

    private final Expression<T> condition;
    private final Expression<T> mustBe;

    public ConditionalExpression(Expression<T> condition, Expression<T> mustBe) {
        this.condition = condition;
        this.mustBe = mustBe;
    }

    public TruthValue isApplicable(@Nonnull T subject) {
        return condition.apply(subject);
    }

    public TruthValue apply(@Nonnull T subject) {
        return mustBe.apply(subject);
    }
}