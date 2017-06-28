package com.jquartz.rich.validation.core.expression;

import com.jquartz.rich.validation.core.evaluation.TruthValue;
import com.jquartz.rich.validation.core.evaluation.trust.Trustworthiness;
import com.jquartz.rich.validation.core.rule.ClassField;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jquartz.rich.validation.core.api.textual.Tokens.ENSURE_THAT;
import static com.jquartz.rich.validation.core.api.textual.Tokens.WHEN;

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

    public TruthValue apply(@Nonnull T subject, Trustworthiness trustworthiness) {
        return mustBe.apply(subject, trustworthiness);
    }

    public String getTextualRepresentation() {
        return ENSURE_THAT + " " + mustBe.getTextualRepresentation() + " "
                + WHEN + " " + condition.getTextualRepresentation();
    }

    @Override
    public String toString() {
        return getTextualRepresentation();
    }

    public Collection<ClassField<?, T>> getAccomplices() {
        return Stream.of(condition.getAccomplices(), mustBe.getAccomplices())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}